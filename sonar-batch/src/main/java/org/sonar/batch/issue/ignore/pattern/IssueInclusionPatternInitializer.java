/*
 * SonarQube, open source software quality management tool.
 * Copyright (C) 2008-2013 SonarSource
 * mailto:contact AT sonarsource DOT com
 *
 * SonarQube is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * SonarQube is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package org.sonar.batch.issue.ignore.pattern;

import org.sonar.batch.issue.ignore.IssueExclusionsConfiguration;

import com.google.common.collect.Maps;
import org.sonar.api.config.Settings;

import java.util.Map;

public class IssueInclusionPatternInitializer extends AbstractPatternInitializer {

  private Map<String, String> pathForComponent;

  public IssueInclusionPatternInitializer(Settings settings) {
    super(settings);
    pathForComponent = Maps.newHashMap();
  }

  @Override
  protected String getMulticriteriaConfigurationKey() {
    return IssueExclusionsConfiguration.PATTERNS_MULTICRITERIA_INCLUSION_KEY;
  }

  @Override
  public void initializePatternsForPath(String relativePath, String componentKey) {
    pathForComponent.put(componentKey, relativePath);
  }

  public String getPathForComponent(String componentKey) {
    return pathForComponent.get(componentKey);
  }
}
