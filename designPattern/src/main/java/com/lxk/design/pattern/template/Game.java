package com.lxk.design.pattern.template;

/**
 * 模板方法设置为 final，这样它就不会被重写
 *
 * @author LiXuekai on 2020/7/24
 */
public abstract class Game {
    /**
     * 初始化游戏
     */
    abstract void initialize();

    /**
     * 开始游戏
     */
    abstract void startPlay();

    /**
     * 结束游戏
     */
    abstract void endPlay();

    /**
     * 模板
     */
    public final void play() {
        initialize();
        startPlay();
        endPlay();
    }
}
