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

package com.ait.lienzo.charts.client.core.xy.line.animation;

import com.ait.lienzo.charts.client.core.animation.PointsAnimationProperty;
import com.ait.lienzo.charts.client.core.xy.animation.XYChartClearAnimation;
import com.ait.lienzo.charts.client.core.xy.line.LineChart;
import com.ait.lienzo.charts.shared.core.types.ChartDirection;
import com.ait.lienzo.client.core.Attribute;
import com.ait.lienzo.client.core.animation.*;
import com.ait.lienzo.client.core.shape.Circle;
import com.ait.lienzo.client.core.shape.Line;
import com.ait.lienzo.client.core.types.Point2D;

import java.util.List;
import java.util.Map;

/**
 * <p>Clears the Line Chart children shapes by animating them to positions /size that perform a clear visual effect..</p>
 *
 */
public class LineChartClearAnimation extends XYChartClearAnimation
{
    public LineChartClearAnimation(final LineChart lineChart, final AnimationTweener tweener, final double duration, final IAnimationCallback callback)
    {
        super(lineChart, tweener, duration, callback);
        
        init(lineChart.getChartWidth(), lineChart.getChartHeight());
    }

    protected LineChart getLineChart()
    {
        return (LineChart) getNode();
    }

    @Override
    protected void clearValues(double chartWidth, double chartHeight) {
        final Map<String, List<Line>> seriesValues = getLineChart().getSeriesValues();
        final Map<String, List<Circle>> seriesPoints = getLineChart().getSeriesPoints();
        
        final double yClearPos = chartHeight;
        final double xClearPos = ChartDirection.POSITIVE.equals(getLineChart().getDirection()) ? 0 : chartWidth;

        // Apply animation to lines.
        if (seriesValues != null)
        {
            for (Map.Entry<String, List<Line>> entry : seriesValues.entrySet())
            {
                for (Line line : entry.getValue())
                {
                    final Point2D p1 = line.getPoint2DArray().get(0);
                    final Point2D p2 = line.getPoint2DArray().get(1);
                    PointsAnimationProperty points = null;
                    if (isVertical()) points = new PointsAnimationProperty(new Point2D(p1.getX(), yClearPos), new Point2D(p2.getX(), yClearPos), Attribute.POINTS);
                    else points = new PointsAnimationProperty(new Point2D(xClearPos, p1.getY()), new Point2D(xClearPos, p2.getY()), Attribute.POINTS);

                    final AnimationProperties animationProperties = new AnimationProperties();
                    animationProperties.push(points);
                    add(line, animationProperties);
                }
            }
        }

        // Apply animation to points.
        if (seriesPoints != null)
        {
            for (Map.Entry<String, List<Circle>> entry : seriesPoints.entrySet())
            {
                for (Circle circle : entry.getValue())
                {
                    if (isVertical()) add(circle, buildAnimationProperties(null, yClearPos, null, 0d));
                    else add(circle, buildAnimationProperties(xClearPos, null));
                }
            }
        }
    }
}
