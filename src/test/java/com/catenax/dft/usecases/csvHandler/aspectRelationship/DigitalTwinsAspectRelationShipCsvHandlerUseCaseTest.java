package com.catenax.dft.usecases.csvHandler.aspectRelationship;

import com.catenax.dft.entities.database.AspectEntity;
import com.catenax.dft.entities.digitalTwins.response.ShellDescriptorResponse;
import com.catenax.dft.entities.digitalTwins.response.ShellLookupResponse;
import com.catenax.dft.entities.usecases.Aspect;
import com.catenax.dft.entities.usecases.AspectRelationship;
import com.catenax.dft.gateways.database.AspectRepository;
import com.catenax.dft.gateways.external.DigitalTwinGateway;
import com.catenax.dft.mapper.AspectMapper;
import com.catenax.dft.usecases.logs.FailureLogsUseCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class DigitalTwinsAspectRelationShipCsvHandlerUseCaseTest {


    @Mock
    private DigitalTwinGateway gateway;
    @Mock
    private AspectRepository aspectRepository;
    @Mock
    private AspectMapper aspectMapper;

    @InjectMocks
    private DigitalTwinsAspectRelationShipCsvHandlerUseCase useCase;


    @Test
    public void run_noShellsRegistered_createShell(){
        //arrange
        final String processId="processID";
        AspectRelationship aspectRelationship = AspectRelationship.builder()
                .processId(processId)
                .parentUuid("parentuuid")
                .parentManufacturerPartId("parentManufacturerPartId")
                .parentPartInstanceId("parentPartInstanceId")
                .build();

        ShellLookupResponse shellLookupResponse = new ShellLookupResponse();
        when(gateway.shellLookup(any())).thenReturn(shellLookupResponse);
        AspectEntity aspectEntity = new AspectEntity();


        when(aspectRepository.findByIdentifiers(anyString(), anyString(), any(), any()))
                .thenReturn(aspectEntity);
        Aspect aspect = Aspect.builder().build();
        when(aspectMapper.mapFrom(aspectEntity)).thenReturn(aspect);

        ShellDescriptorResponse shellDescriptorResponse = new ShellDescriptorResponse();
        shellDescriptorResponse.setIdentification("identification");
        when(gateway.createShellDescriptor(any())).thenReturn(shellDescriptorResponse);
        //act
        useCase.run(aspectRelationship, processId);

        //assert
        verify(gateway,only()).createShellDescriptor(any());
    }


}