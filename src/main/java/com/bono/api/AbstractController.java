package com.bono.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bono on 9/4/16.
 */
abstract class AbstractController {

    protected ClientExecutor clientExecutor;

    private CommandList commandList;

    public AbstractController(ClientExecutor clientExecutor) {
        this.clientExecutor = clientExecutor;
    }

    public CommandList sendCommandList() {
        if (commandList == null) {
            commandList = new CommandList();
        }
        commandList.init();
        return commandList;
    }

    public class CommandList {

        List<Command> list = new ArrayList<>();

        protected void init() {
            list.clear();
            list.add(new DefaultCommand(DefaultCommand.COMMAND_LIST_BEGIN));
            list.add(new DefaultCommand(DefaultCommand.COMMAND_LIST_END));
        }

        public CommandList add(String command, String... args) {
            list.add((list.size() -1), new DefaultCommand(command, args));
            return this;
        }

        public void send() throws IOException {
            if (clientExecutor == null) {
                throw new NullPointerException("ClientExecutor can not be null in: " + getClass().toString());
            }

            clientExecutor.executeList(list);
        }
    }
}
