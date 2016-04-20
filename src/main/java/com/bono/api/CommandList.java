package com.bono.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hendriknieuwenhuis on 17/03/16.
 */
public class CommandList {

    public static final String COMMAND_LIST_BEGIN = "command_list_begin";
    public static final String COMMAND_LIST_OK_BEGIN = "command_list_ok_begin";
    public static final String COMMAND_LIST_END = "command_list_end";

    private Command[] commands;

    private static CommandList commandList;

    private CommandList() {}

    private CommandList(String begin) {
        commands = new Command[]{new DefaultCommand(begin)};
    }

    public static CommandList commandList(String begin) {
        if (commandList == null) {
            commandList = new CommandList(begin);
        }
        return commandList;
    }

    public void addCommand(Command command) {
        Command[] temp = new Command[commands.length + 1];
        System.arraycopy(commands, 0, temp, 0, commands.length);
        System.arraycopy(command, 0, temp, (commands.length -1),1);
        commands = temp;
    }

    public String getCommandListBytes() {
        byte[] bytes = null;

        for (Command command : commands) {
            if (bytes == null) {
                bytes = command.getCommandBytes();
            } else {
                byte[] temp = new byte[bytes.length + command.getCommandBytes().length];
                System.arraycopy(command.getCommandBytes(), 0, temp, bytes.length, );
            }
        }
    }


}
