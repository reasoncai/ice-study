import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import Ice.Communicator;
import Ice.ObjectPrx;

public class IceClientUtil {
	private static volatile Ice.Communicator ic = null;
	private static Map<Class,ObjectPrx> cls2PrxMap = new HashMap<Class,ObjectPrx>();
	private static volatile long lastAccessTimeStamp;
	private static volatile MonitorThread monitorThread;
	private static long idleTimeOutSeconds = 0;
	private static String iceLocator = null;
	private static final String locatorKey = "--Ice.Default.Locator";
	

	/**
	 *获取Communicator（延迟加载）
	 * @return
	 */
	public static Ice.Communicator getCommunicator(){
		if(ic == null){
			synchronized (IceClientUtil.class) {
				if(ic == null){
					if(iceLocator == null){
						ResourceBundle rb = ResourceBundle.getBundle("iceclient",Locale.ENGLISH);
						iceLocator = rb.getString(locatorKey);
						idleTimeOutSeconds = Integer.parseInt(rb.getString("idleTimeOutSeconds"));
						String[] initParams = new String[] {locatorKey + "=" + iceLocator};
						ic = Ice.Util.initialize(initParams);
						//创建用于释放资源的守护线程
						createMonitorThread();
					}
				}
			}
		}
		//记录当前时间为最近一次使用时间
		lastAccessTimeStamp = System.currentTimeMillis();
		return ic;
	}
	
	/**
	 * 获取Ice服务实例
	 * @param serviceCls
	 * @return Object
	 */
	public static ObjectPrx getServicePrx(Class serviceCls){
		ObjectPrx proxy = cls2PrxMap.get(serviceCls);
		if(proxy != null){
			lastAccessTimeStamp = System.currentTimeMillis();
			return proxy;
		}
		proxy = createIceProxy(getCommunicator(),serviceCls);
		cls2PrxMap.put(serviceCls, proxy);
		lastAccessTimeStamp = System.currentTimeMillis();
		return proxy;
	}
	private static ObjectPrx createIceProxy(Communicator communicator, Class serviceCls) {
		ObjectPrx proxy = null;
		String clsName = serviceCls.getName();
		String serviceName = serviceCls.getSimpleName();
		int pos = serviceName.lastIndexOf("Prx");
		if(pos<=0){
			throw new java.lang.IllegalArgumentException("Invalid ObjectPrx class,class name must end with Prx");
		}
		String realName = serviceName.substring(0, pos);
		try {
			ObjectPrx base = communicator.stringToProxy(realName);
			//通过反射创建Helper类
			proxy = (ObjectPrx)Class.forName(clsName+"Helper").newInstance();
			Method method = proxy.getClass().getDeclaredMethod("uncheckedCast", ObjectPrx.class);
			proxy = (ObjectPrx)method.invoke(proxy, base);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return proxy;

	}
	/**
	 * 创建用于释放资源的守护线程
	 */
	private static void createMonitorThread() {
		monitorThread = new MonitorThread();
		monitorThread.setDaemon(true);
		monitorThread.start();
	}

	/**
	 * 
	 *用于定时释放资源的线程类
	 */
	static class MonitorThread extends Thread{
		@Override
		public void run() {
			while(!Thread.currentThread().isInterrupted()){
				try {
					Thread.sleep(5000L);
					//超时，关闭communicator对象，释放资源
					if(System.currentTimeMillis() > lastAccessTimeStamp + idleTimeOutSeconds * 1000L){
						closeCommunicator(true);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 关闭Communicator，清空缓存proxy
	 * @param removeServiceCache
	 */
	public static void closeCommunicator(boolean removeServiceCache) {
		synchronized (IceClientUtil.class) {
			if(ic != null){
				safeShutdown();
				monitorThread.interrupt();
				if(removeServiceCache && cls2PrxMap.isEmpty()){
					try {
						cls2PrxMap.clear();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * 关闭Communicator
	 */
	private static void safeShutdown() {
		try {
			ic.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ic.destroy();
			ic = null;
		}
	}
}
