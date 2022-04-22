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

import com.catenax.dft.entities.database.AspectEntity;
import com.catenax.dft.entities.usecases.Aspect;
import com.catenax.dft.gateways.database.AspectRepository;
import com.catenax.dft.mapper.AspectMapper;
import com.catenax.dft.usecases.csvHandler.AbstractCsvHandlerUseCase;
import com.catenax.dft.usecases.logs.FailureLogUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StoreAspectCsvHandlerUseCase extends AbstractCsvHandlerUseCase<Aspect, Aspect> {

    private final AspectRepository aspectRepository;
    private final AspectMapper aspectMapper;

    public StoreAspectCsvHandlerUseCase(AspectRepository aspectRepository, AspectMapper mapper, FailureLogUseCase failureLogUseCase) {
        super(null, failureLogUseCase);
        this.aspectRepository = aspectRepository;
        this.aspectMapper = mapper;
    }

    protected Aspect executeUseCase(Aspect input, String processId) {
        AspectEntity entity = aspectMapper.mapFrom(input);
        aspectRepository.save(entity);

        return input;
    }
}