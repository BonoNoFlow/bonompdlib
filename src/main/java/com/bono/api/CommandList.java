package com.bono.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hendriknieuwenhuis on 17/03/16.
 */
public class CommandList implements Iterable {

    public static final String COMMAND_LIST_BEGIN = "command_list_begin";
    public static final String COMMAND_LIST_OK_BEGIN = "command_list_ok_begin";
    public static final String COMMAND_LIST_END = "command_list_end";

    private DefaultCommand[] commands;

    private static CommandList commandList;

    private CommandList(String begin) {
        commands = new DefaultCommand[1];
        commands[0] = new DefaultCommand(begin);
    }

    public static CommandList commandList(String begin) {
        if (commandList == null) {
            commandList = new CommandList(begin);
        }
        return commandList;
    }

    public void addCommand(DefaultCommand command) {
        DefaultCommand[] temp = new DefaultCommand[commands.length + 1];
        DefaultCommand[] add = new DefaultCommand[]{command};

        System.arraycopy(commands, 0, temp, 0, commands.length);
        System.arraycopy(add, 0, temp, commands.length ,1);

        commands = temp;
    }

    public byte[] getCommandListBytes() {
        byte[] bytes = null;

        for (DefaultCommand command : commands) {
            if (bytes == null) {
                bytes = command.getCommandBytes();
            } else {
                byte[] temp = new byte[(bytes.length + command.getCommandBytes().length)];
                System.arraycopy(bytes, 0, temp, 0, bytes.length);
                System.arraycopy(command.getCommandBytes(), 0, temp, bytes.length, command.getCommandBytes().length);
                bytes = temp;
            }
        }
        return bytes;
    }

    public void commandlistEnd() {
        DefaultCommand defaultCommand = new DefaultCommand(COMMAND_LIST_END);
        addCommand(defaultCommand);
    }

    public void executeCommandList(DBExecutor dbExecutor) throws Exception {

    }

    @Override
    public Iterator iterator() {
        return new CommandListIterator();
    }


    private class CommandListIterator implements Iterator {

        private int count = 0;

        @Override
        public boolean hasNext() {
            if (count < size()) {
                return true;
            }
            return  false;
        }

        @Override
        public Object next() {
            return commands[count++];
        }

        @Override
        public void remove() {

        }
    }

    public int size() {
        return commands.length;
    }


}
