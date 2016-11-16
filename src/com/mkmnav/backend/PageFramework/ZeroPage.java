package com.mkmnav.backend.PageFramework;

import com.mkmnav.backend.Motor;

/**
 * Created by chapa on 15-11-2016.
 */

public abstract class ZeroPage {

    protected Motor.PAGETYPES pageType;
    protected Motor.MAINSITEURLS pageURL;

    public Motor.PAGETYPES getPageType(){
        return pageType;
    }

    public Motor.MAINSITEURLS getPageURL(){
        return pageURL;
    }

    public ZeroPage(Motor.PAGETYPES pageType, Motor.MAINSITEURLS pageURL){
        this.pageType = pageType;
        this.pageURL = pageURL;
    }

}
