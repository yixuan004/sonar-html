/*
 * SonarSource HTML analyzer :: Sonar Plugin
 * Copyright (c) 2010-2023 SonarSource SA and Matthijs Galesloot
 * sonarqube@googlegroups.com
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
package org.sonar.plugins.html.checks.custom;

import org.sonar.check.Rule;
import org.sonar.plugins.html.checks.AbstractPageCheck;
import org.sonar.plugins.html.node.Node;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Rule(key = "FetchValueBracketUtextCheck")
public class FetchValueBracketUtextCheck extends AbstractPageCheck {

    // 匹配相对应的case
    private String fetchValueRegex = "\\[\\s*\\(\\s*\\$\\s*\\{.*\\}\\s*\\)\\s*\\]|th\\s*:\\s*utext";
    Pattern fetchValuePattern = Pattern.compile(fetchValueRegex);

    @Override
    public void startDocument(List<Node> nodes) {
        for (Node node: nodes) {
            Matcher matcher = fetchValuePattern.matcher(node.toString());
            if (matcher.find()) {
                createViolation(node.getStartLinePosition(), "ABC_CUSTOM: should not use [(${var1})] or th:utext fetch value");
            }
        }
    }
}
