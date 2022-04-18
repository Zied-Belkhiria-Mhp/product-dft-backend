package com.catenax.dft.usecases.aspectRelationship;

import com.catenax.dft.entities.database.AspectEntity;
import com.catenax.dft.entities.database.AspectRelationshipEntity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class GetAspectsRelationshipUseCaseTest {

    @Mock
    AspectRelationshipEntity aspectRelationshipEntity;
    @Mock
    AspectEntity aspectEntity;
    @Mock
    private GetAspectsRelationshipUseCase getAspectsRelationshipUseCase;

    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void execute_IdNotExists() {
        //quero que retorne um mapa vazio
        Mockito.when(getAspectsRelationshipUseCase.execute("AA")).thenReturn(null);
    }
}