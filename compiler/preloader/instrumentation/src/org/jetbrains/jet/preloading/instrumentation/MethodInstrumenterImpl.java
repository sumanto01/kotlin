/*
 * Copyright 2010-2013 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.jet.preloading.instrumentation;

import java.util.List;
import java.util.regex.Pattern;

class MethodInstrumenterImpl implements MethodInstrumenter {
    private final Pattern namePattern;
    private final Pattern descPattern;
    private final boolean allowMultipleMatches;
    private final List<MethodData> enterData;
    private final List<MethodData> exitData;

    public MethodInstrumenterImpl(
            Pattern namePattern,
            Pattern descPattern,
            boolean allowMultipleMatches,
            List<MethodData> enterData,
            List<MethodData> exitData
    ) {
        this.namePattern = namePattern;
        this.descPattern = descPattern;
        this.allowMultipleMatches = allowMultipleMatches;
        this.enterData = enterData;
        this.exitData = exitData;
    }

    @Override
    public boolean allowsMultipleMatches() {
        return allowMultipleMatches;
    }

    @Override
    public boolean isApplicable(String name, String desc) {
        return namePattern.matcher(name).matches() && descPattern.matcher(desc).matches();
    }

    @Override
    public List<MethodData> getEnterData() {
        return enterData;
    }

    @Override
    public List<MethodData> getExitData() {
        return exitData;
    }

    @Override
    public String toString() {
        return namePattern + " " + descPattern + (allowMultipleMatches ? "[multiple]" : "");
    }
}
