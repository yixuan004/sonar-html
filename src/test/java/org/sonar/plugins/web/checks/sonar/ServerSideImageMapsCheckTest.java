/*
 * SonarSource :: Web :: Sonar Plugin
 * Copyright (c) 2010-2016 SonarSource SA and Matthijs Galesloot
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
package org.sonar.plugins.web.checks.sonar;


import java.io.File;

import org.junit.Rule;
import org.junit.Test;
import org.sonar.plugins.web.checks.CheckMessagesVerifierRule;
import org.sonar.plugins.web.checks.TestHelper;
import org.sonar.plugins.web.visitor.WebSourceCode;

public class ServerSideImageMapsCheckTest {

  @Rule
  public CheckMessagesVerifierRule checkMessagesVerifier = new CheckMessagesVerifierRule();

  @Test
  public void detected() throws Exception {
    WebSourceCode sourceCode = TestHelper.scan(new File("src/test/resources/checks/ServerSideImageMapsCheck.html"), new ServerSideImageMapsCheck());

    checkMessagesVerifier.verify(sourceCode.getIssues())
        .next().atLine(1).withMessage("Use the \"map\" tag and \"area\" tags instead.");
  }

}
