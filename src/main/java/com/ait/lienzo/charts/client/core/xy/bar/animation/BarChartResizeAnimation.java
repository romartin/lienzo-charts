
package com.ait.lienzo.charts.client.core.xy.bar.animation;

import com.ait.lienzo.charts.client.core.animation.StackedTweeningAnimation;
import com.ait.lienzo.charts.client.core.xy.axis.AxisLabel;
import com.ait.lienzo.charts.client.core.xy.bar.BarChart;
import com.ait.lienzo.charts.client.core.xy.bar.BarChartLabel;
import com.ait.lienzo.client.core.animation.AnimationTweener;
import com.ait.lienzo.client.core.animation.IAnimationCallback;
import com.ait.lienzo.client.core.shape.Line;
import com.ait.lienzo.client.core.shape.Rectangle;
import com.ait.lienzo.client.core.shape.Text;
import com.ait.lienzo.client.core.types.Point2D;
import com.ait.lienzo.client.core.types.Point2DArray;
import com.ait.lienzo.shared.core.types.IColor;

public class BarChartResizeAnimation extends AbstractBarChartAnimation
{
    public BarChartResizeAnimation(final BarChart barChart, final double chartWidth, final double chartHeight, final AnimationTweener tweener, final double duration, final IAnimationCallback callback)
    {
        super(barChart, chartWidth, chartHeight, tweener, duration, callback);
        add(barChart, buildAnimationProperties(null, null, chartWidth, chartHeight));
        reloadBuilders(chartWidth, chartHeight);
    }

    protected void reloadBuilders(final double chartWidth, final double chartHeight)
    {
        getBarChart().getCategoriesAxisBuilder().reload(isVertical() ? chartWidth : chartHeight);
        getBarChart().getValuesAxisBuilder().reload(isVertical() ? chartHeight : chartWidth);
    }

    @Override
    protected boolean apply(final StackedTweeningAnimation.NodeAnimation nodeAnimation, final double percent)
    {
        boolean apply = super.apply(nodeAnimation, percent);
        final double w = getBarChart().getChartWidth();
        final double h = getBarChart().getChartHeight();
        reloadBuilders(w, h);
        calculate(w, h);
        return apply;
    }

    @Override
    protected void doAnimateCategoriesAxisTitle(final Text categoriesAxisTitle, final Double x, final Double y)
    {
        setShapeAttributes(categoriesAxisTitle, x, y);
    }

    @Override
    protected void doAnimateValuesAxisTitle(final Text valuesAxisTitle, final Double x, final Double y)
    {
        setShapeAttributes(valuesAxisTitle, x, y);
    }

    @Override
    protected void doAnimateCategoriesAxisIntervals(final BarChartLabel chartLabel, final AxisLabel axisLabel, final Double x, final Double y)
    {
        chartLabel.setAttributes(axisLabel, x, y, null, null, false);
    }

    @Override
    protected void doAnimateValuesAxisIntervals(final Line valueAxisInterval, final double p00, final double p01, final double p10, final double p11)
    {
        if (valueAxisInterval != null) valueAxisInterval.setPoints(new Point2DArray(new Point2D(p00, p01), new Point2D(p10, p11)));
    }

    @Override
    protected void doAnimateValuesAxisIntervals(final BarChartLabel chartLabel, final AxisLabel axisLabel, final Double x, final Double y)
    {
        chartLabel.setAttributes(axisLabel, x, y, null, null, false);
    }

    @Override
    protected void doAnimateValues(final Rectangle bar, final Double x, final Double y, final Double w, final Double h, final IColor color, final Double alpha, final boolean isSeriesNew)
    {
        setShapeAttributes(bar, x, y, w, h, color, alpha);
    }
}
