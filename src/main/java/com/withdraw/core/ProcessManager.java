package com.withdraw.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class ProcessManager {
    private final static Map<String, ProcessBase> mapProcess = new HashMap<String, ProcessBase>();

    public static void startAllProcess(){
        Set<String> processTypes = mapProcess.keySet();
        for (String processType : processTypes) {
            ProcessBase process = mapProcess.get(processType);

            }
        }
    }
