package com.ericlam.mc.playerstate.config;

import com.ericlam.mc.eld.annotations.Resource;
import com.ericlam.mc.eld.components.Configuration;

@Resource(locate = "config.yml")
public class PlayerStateConfig extends Configuration {

    public String validateRegex;

    //public int cooldown;

}
