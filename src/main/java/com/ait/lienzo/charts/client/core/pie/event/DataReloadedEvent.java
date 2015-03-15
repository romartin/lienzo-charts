
package com.ait.lienzo.charts.client.core.pie.event;

import com.ait.lienzo.charts.client.core.pie.PieChart;
import com.google.gwt.event.shared.GwtEvent;

public class DataReloadedEvent extends GwtEvent<DataReloadedEventHandler>
{
    public static Type<DataReloadedEventHandler> TYPE = new Type<DataReloadedEventHandler>();

    private final PieChart                       chart;

    public DataReloadedEvent(PieChart chart)
    {
        this.chart = chart;
    }

    @Override
    public Type<DataReloadedEventHandler> getAssociatedType()
    {
        return TYPE;
    }

    @Override
    protected void dispatch(DataReloadedEventHandler handler)
    {
        handler.onDataReloaded(this);
    }

    public PieChart getChart()
    {
        return chart;
    }
}