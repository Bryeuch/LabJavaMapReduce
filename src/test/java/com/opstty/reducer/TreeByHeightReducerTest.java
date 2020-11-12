package com.opstty.reducer;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TreeByHeightReducerTest {
    @Mock
    private Reducer.Context context;
    private TreeByHeightReducer treeByHeightReducer;

    @Before
    public void setup() {
        this.treeByHeightReducer = new TreeByHeightReducer();
    }

    @org.junit.Test
    public void testMap() throws IOException, InterruptedException {
        ArrayList<DoubleWritable> value = new ArrayList<DoubleWritable>();
        value.add(new DoubleWritable(13.0));
        value.add(new DoubleWritable(40.0));
        value.add(new DoubleWritable(19.0));

        this.treeByHeightReducer.reduce(null, value, this.context);
        verify(this.context, times(3))
                .write(NullWritable.get(), new DoubleWritable(40.0)); // this check the last print values of our reducer. Our sorted values are: 13.0, 19.0 and 40.0 so if the last value printed is 40.0 our reducer works
    }
}