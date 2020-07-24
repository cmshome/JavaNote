package com.lxk.design.pattern.template;

/**
 * Cricket
 * n. 板球，板球运动；蟋蟀
 *
 * @author LiXuekai on 2020/7/24
 */
public class Cricket extends Game {

    @Override
    void initialize() {
        System.out.println("Cricket Game Initialized! Start playing.");
    }

    @Override
    void startPlay() {
        System.out.println("Cricket Game Started. Enjoy the game!");
    }

    @Override
    void endPlay() {
        System.out.println("Cricket Game Finished!");
    }
}
