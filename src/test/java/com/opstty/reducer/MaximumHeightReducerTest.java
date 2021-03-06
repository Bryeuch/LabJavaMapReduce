package com.opstty.reducer;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MaximumHeightReducerTest {
    @Mock
    private Reducer.Context context;
    private MaximumHeightReducer maximumHeightReducer;

    @Before
    public void setup() {
        this.maximumHeightReducer = new MaximumHeightReducer();
    }

    @org.junit.Test
    public void testMap() throws IOException, InterruptedException {
        DoubleWritable v1 = new DoubleWritable(10);
        DoubleWritable v2 = new DoubleWritable(5);
        DoubleWritable v3 = new DoubleWritable(13);
        Iterable<DoubleWritable> value = Arrays.asList(v1, v2, v3);

        this.maximumHeightReducer.reduce(new Text("pomifera"), value, this.context);
        verify(this.context, times(1))
                .write(new Text("pomifera"), new DoubleWritable(13.0));
    }
}