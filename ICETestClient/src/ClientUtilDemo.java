import com.my.demo.book.Message;
import com.my.demo.book.OnlineBookPrx;

import Ice.ObjectPrx;

public class ClientUtilDemo {
	public static void main(String[] args) {
		OnlineBookPrx servicePrx = (OnlineBookPrx)IceClientUtil.getServicePrx(OnlineBookPrx.class);
		Message message = new Message();
		message.content = "fuck55you";
		message.name = "ok";
		Message tick = servicePrx.bookTick(message);
		System.out.println(tick.toString());
	}
}
