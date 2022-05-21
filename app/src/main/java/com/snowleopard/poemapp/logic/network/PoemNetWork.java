package com.snowleopard.poemapp.logic.network;

public class PoemNetWork {
    private UserService userService = ServiceCreator.create(UserService.class);

    private PoemService poemService = ServiceCreator.create(PoemService.class);


}
