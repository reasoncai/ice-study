[["java:package:com.my.demo"]]
module book {
struct Message{
string name;
int type;
bool valid;
double price;
string content;
};
	interface OnlineBook{
		Message bookTick(Message msg);
	};
};