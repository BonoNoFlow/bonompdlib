package com.bono.api;

import java.util.Arrays;

/**
 * Created by hendriknieuwenhuis on 15/08/15.
 *
 * public static final String COMMAND_LIST_BEGIN = "command_list_begin";
 * public static final String COMMAND_LIST_OK_BEGIN = "command_list_ok_begin";
 * public static final String COMMAND_LIST_END = "command_list_end";
 *
 *
 *
 * veranderen naar mpdcommand. command interface getCommandstring wissen.
 */
public class DefaultCommand implements Command {

    public static final String COMMAND_LIST_BEGIN = "command_list_begin";
    public static final String COMMAND_LIST_OK_BEGIN = "command_list_ok_begin";
    public static final String COMMAND_LIST_END = "command_list_end";

    protected String command;
    protected String[] args;

    public DefaultCommand(String command) {
        this.command = command;
    }

    public DefaultCommand(String command, String... args) {
        this(command);

        if (args != null) {
            this.args = new String[args.length];
            int i = 0;
            for (String arg : args) {
                this.args[i++] = arg;
            }
        }
    }

    /**
     * Returns the command, request and arguments as a
     * array of bytes.
     * @return byte[]
     */
    @Override
    public byte[] getCommandBytes() {

        String outCommand = null;
        if (command != null) {
            outCommand = command;
            if (args != null) {
                for (String arg : args) {
                    outCommand = outCommand + " " + arg;
                }
            }
        } else {
            return null;
        }
        outCommand += "\n";
        return outCommand.getBytes();
    }

    @Override
    public String getCommandString() {
        return command;
    }

    @Deprecated
    public void addArg(String arg) {
        if (args == null) {
            args = new String[]{arg};
        } else {
            String[] source = new String[]{arg};
            String[] array = new String[(args.length + 1)];
            System.arraycopy(args, 0, array, 0, args.length);
            System.arraycopy(source, 0, array, args.length, source.length);
            args = array;
        }
    }

    @Deprecated
    public void addArgs(String[] args) {
        if (args == null) {
            this.args = args;
        } else {
            String[] array = new String[(this.args.length + args.length)];
            System.arraycopy(this.args, 0, array, 0, this.args.length);
            System.arraycopy(args, 0, array, this.args.length, args.length);
            this.args = array;
        }
    }

    public String[] getArgs() {
        return args;
    }


    @Override
    public String toString() {
        return "ServerCommand{" +
                "args=" + Arrays.toString(args) +
                ", request='" + command + '\'' +
                '}';
    }

}
