package com.revature.rideshare.batchservice.services.impl;

import com.revature.rideshare.batchservice.models.Batch;
import com.revature.rideshare.batchservice.repositories.BatchRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class BatchServiceImplTest {
    @InjectMocks
    private BatchServiceImpl batchService;

    @Mock
    private BatchRepository batchRepository;

    @Test
    public void testGettingBatches() {
        List<Batch> batches = new ArrayList<>();
        batches.add(new Batch());
        batches.add(new Batch());

        when(batchRepository.findAll()).thenReturn(batches);

        assertEquals(2, batchService.getBatches().size());
    }

    @Test
    public void testGettingBatchByNumber() {
        Batch expected = new Batch(123, "location");

        when(batchRepository.findById(123)).thenReturn(Optional.of(expected));

        Optional<Batch> actual = batchService.getBatchByNumber(123);
        if (actual.isPresent()) assertEquals(expected, actual.get());
        else fail();
    }

    @Test
    public void testGettingBatchesByLocation() {
        List<Batch> batches = new ArrayList<>();
        batches.add(new Batch(123, "location"));
        batches.add(new Batch(456, "location"));

        when(batchRepository.getBatchByLocation("location")).thenReturn(batches);

        assertEquals(2, batchService.getBatchByLocation("location").size());
    }

    @Test
    public void testAddingBatch() {
        Batch expected = new Batch(123, "location");

        when(batchRepository.save(expected)).thenReturn(expected);

        Batch actual = batchService.addBatch(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void testUpdatingBatch() {
        Batch expected = new Batch(123, "location");

        when(batchRepository.save(expected)).thenReturn(expected);

        Batch actual = batchService.updateBatch(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void testDeletingBatch() {
        String expected = "Batch number: 123 was deleted.";

        when(batchRepository.existsById(123)).thenReturn(true);

        String actual = batchService.deleteBatchByNumber(123);
        assertEquals(expected, actual);
    }
}
