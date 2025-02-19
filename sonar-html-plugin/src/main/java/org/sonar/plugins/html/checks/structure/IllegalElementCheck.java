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
package org.sonar.plugins.html.checks.structure;

import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;
import org.sonar.plugins.html.checks.AbstractPageCheck;
import org.sonar.plugins.html.node.Node;
import org.sonar.plugins.html.node.TagNode;

import java.util.List;

@Rule(key = "IllegalElementCheck")
public class IllegalElementCheck extends AbstractPageCheck {

  private static final String DEFAULT_ELEMENTS = "";

  @RuleProperty(
    key = "elements",
    description = "Comma-separated list of names of forbidden elements",
    defaultValue = DEFAULT_ELEMENTS)
  public String elements = DEFAULT_ELEMENTS;

  private String[] elementsArray;

  @Override
  public void startDocument(List<Node> nodes) {
    elementsArray = trimSplitCommaSeparatedList(elements);
  }

  @Override
  public void startElement(TagNode element) {
    for (String elementName : elementsArray) {
      if (elementName.equalsIgnoreCase(element.getLocalName()) || elementName.equalsIgnoreCase(element.getNodeName())) {
        createViolation(element, "Remove this \"" + elementName + "\" element.");
      }
    }
  }

}
