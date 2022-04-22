/*
 * Copyright 2022 CatenaX
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.catenax.dft.usecases.csvHandler.aspects;

import com.catenax.dft.entities.usecases.Aspect;
import com.catenax.dft.usecases.common.UUIdGenerator;
import com.catenax.dft.usecases.csvHandler.AbstractCsvHandlerUseCase;
import com.catenax.dft.usecases.csvHandler.exceptions.UseCaseValidationException;
import com.catenax.dft.usecases.logs.FailureLogsUseCase;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class GenerateUuIdCsvHandlerUseCase extends AbstractCsvHandlerUseCase<Aspect, Aspect> {

    public GenerateUuIdCsvHandlerUseCase(DigitalTwinsAspectCsvHandlerUseCase nextUseCase,
                                         FailureLogsUseCase failureLogsUseCase) {
        super(nextUseCase, failureLogsUseCase);
    }

    @SneakyThrows
    @Override
    protected Aspect executeUseCase(Aspect input, String processId) {
        if (input == null) {
            throw new UseCaseValidationException("Aspect cannot be null");
        }
        if (processId==null){
            throw  new UseCaseValidationException("ProcessId cannot be null");
        }

        if (input.getUuid() == null || input.getUuid().isBlank()) {
            input.setUuid(UUIdGenerator.getUrnUuid());
        }
        return input;
    }
}