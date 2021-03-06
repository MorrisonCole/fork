/*
 * Copyright 2014 Shazam Entertainment Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License.
 *
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.shazam.fork.injector;

import com.shazam.fork.ForkRunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.shazam.fork.Utils.millisSinceNanoTime;
import static com.shazam.fork.injector.DeviceLoaderInjector.deviceLoader;
import static com.shazam.fork.injector.DevicePoolLoaderInjector.devicePoolLoader;
import static com.shazam.fork.injector.DevicePoolRunnerInjector.devicePoolRunner;
import static com.shazam.fork.injector.FilenameCreatorInjector.filenameCreator;
import static com.shazam.fork.injector.RuntimeConfigurationInjector.runtimeConfiguration;
import static com.shazam.fork.injector.SummaryPrinterInjector.summaryPrinter;
import static com.shazam.fork.injector.SwimlaneConsoleLoggerInjector.swimlaneConsoleLogger;
import static com.shazam.fork.injector.TestClassFilterInjector.testClassFilter;
import static com.shazam.fork.injector.TestClassScannerInjector.testClassScanner;
import static java.lang.System.nanoTime;

public class ForkRunnerInjector {

    private static final Logger logger = LoggerFactory.getLogger(ForkRunnerInjector.class);

    public static ForkRunner forkRunner() {
        long startNanos = nanoTime();

        ForkRunner forkRunner = new ForkRunner(
                runtimeConfiguration(),
                deviceLoader(),
                devicePoolLoader(),
                testClassScanner(),
                testClassFilter(),
                devicePoolRunner(),
                swimlaneConsoleLogger(),
                summaryPrinter(),
                filenameCreator());

        logger.debug("Initialization of ForkRunner took: {} milliseconds", millisSinceNanoTime(startNanos));

        return forkRunner;
    }
}
