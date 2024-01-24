package net.starlight.potato_core.ident;

public enum Result {
    /**
     * <p>表示该类影响程序运行，且认为类成功实现在游戏里，应用在游戏里</p>
     */
    SUCCESS,
    /**
     * <p>表示该类不影响程序运行，且认为类没有成功实现在游戏里</p>
     */
    FAIL,
    /**
     * <p>表示该类不影响程序运行，且认为这个类无用</p>
     * <p>或者未加入更详细的注释信息</p>
     */
    USELESS,
    /**
     * <p>表示该类已经弃用</p>
     */
    DEPRECATED
}
