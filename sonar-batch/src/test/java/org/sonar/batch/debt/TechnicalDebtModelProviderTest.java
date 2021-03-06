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

package org.sonar.batch.debt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.sonar.api.technicaldebt.batch.TechnicalDebtModel;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TechnicalDebtModelProviderTest {

  @Mock
  DebtModelLoader loader;

  @Test
  public void load_model() {
    TechnicalDebtModel model = mock(TechnicalDebtModel.class);
    when(loader.load()).thenReturn(model);

    DebtModelProvider provider = new DebtModelProvider();
    TechnicalDebtModel result = provider.provide(loader);
    assertThat(result).isNotNull();
  }

  @Test
  public void load_model_only_once() {
    TechnicalDebtModel model = mock(TechnicalDebtModel.class);
    when(loader.load()).thenReturn(model);

    DebtModelProvider provider = new DebtModelProvider();
    provider.provide(loader);
    verify(loader).load();

    provider.provide(loader);
    verifyZeroInteractions(loader);
  }
}
