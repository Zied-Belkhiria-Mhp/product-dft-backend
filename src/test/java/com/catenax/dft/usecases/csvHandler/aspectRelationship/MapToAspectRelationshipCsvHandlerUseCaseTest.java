package com.catenax.dft.usecases.csvHandler.aspectRelationship;

import com.catenax.dft.entities.csv.RowData;
import com.catenax.dft.entities.usecases.AspectRelationship;
import com.catenax.dft.usecases.csvHandler.exceptions.CsvHandlerUseCaseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class MapToAspectRelationshipCsvHandlerUseCaseTest {

    AspectRelationship aspectRelationship = AspectRelationship.builder()
            .rowNumber(0)
            .processId("urn:uuid:aaa2")
            .parentUuid("urn:uuid:aaa1")
            .parentPartInstanceId("xa")
            .parentManufacturerPartId("xb")
            .parentOptionalIdentifierKey("xc")
            .parentOptionalIdentifierValue("xd")
            .childUuid("xe")
            .childPartInstanceId("xf")
            .childManufacturerPartId("xg")
            .childOptionalIdentifierKey("xh")
            .childOptionalIdentifierValue("xi")
            .lifecycleContext("xj")
            .quantityNumber("2")
            .measurementUnitLexicalValue("xk")
            .dataTypeUri("xl")
            .assembledOn("xm")
            .build();

    RowData data1 = new RowData(0, "urn:uuid:aaa1");
    RowData data2 = new RowData(1, "urn:uuid:aaa2;urn:uuid:aaa1;xa;xb;xc;xd;xe;xf;xg;xh;xi;xj;2;xk;xl");

    MapToAspectRelationshipCsvHandlerUseCase mapToAspectRelationshipCsvHandlerUseCase = new MapToAspectRelationshipCsvHandlerUseCase(null);

    void executeUseCase(){
        // List<AspectRelationship> list = null;
        //Mockito.when(mapToAspectRelationshipCsvHandlerUseCase.executeUseCase(data2, "urn:uuid:aaa1"));

        //list.add(aspectRelationship);
        //Mockito.when(list.size()).thenReturn(1);

        assertEquals(aspectRelationship, mapToAspectRelationshipCsvHandlerUseCase.executeUseCase(data2, "urn:uuid:aaa2"));
    }
    @Test
    void executeUseCase_WithExceptionWrongNumberOfFields(){
       //given(mapToAspectRelationshipCsvHandlerUseCase.executeUseCase(data1, "urn:uuid:aaa1")).willAnswer(
           //     invocation -> {throw new CsvHandlerUseCaseException(data1.position(), "This row has wrong amount of fields");});
        //Mockito.when(mapToAspectRelationshipCsvHandlerUseCase.executeUseCase(data1, "urn:uuid:aaa1")).thenThrow(Exception.class);
        CsvHandlerUseCaseException exception = Assertions.assertThrows(CsvHandlerUseCaseException.class, () -> {
            mapToAspectRelationshipCsvHandlerUseCase.executeUseCase(data1, "urn:uuid:aaa1");
        });
        Assertions.assertEquals("RowPosition: "+data1.position() +" | Description: This row has wrong amount of fields", exception.getMessage());
    }




}