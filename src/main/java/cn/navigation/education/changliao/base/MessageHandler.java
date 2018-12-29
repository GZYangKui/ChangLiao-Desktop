package cn.navigation.education.changliao.base;

@FunctionalInterface
public interface MessageHandler<T> {
     void handler(T k);
}
