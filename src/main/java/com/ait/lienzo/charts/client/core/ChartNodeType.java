/*
   Copyright (c) 2014,2015,2016 Ahome' Innovation Technologies. All rights reserved.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
   
   Author: Roger Martinez - Red Hat
 */

package com.ait.lienzo.charts.client.core;

import com.ait.lienzo.shared.core.types.GroupType;

public class ChartNodeType extends GroupType
{
    public static final ChartNodeType PIE_CHART  = new ChartNodeType("Lienzo.PieChart");

    public static final ChartNodeType BAR_CHART  = new ChartNodeType("Lienzo.BarChart");

    public static final ChartNodeType LINE_CHART = new ChartNodeType("Lienzo.LineChart");

    public ChartNodeType(String value)
    {
        super(value);
    }
}
