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

package com.catenax.dft.usecases.csvHandler;

import com.catenax.dft.entities.database.FailureLogEntity;
import com.catenax.dft.usecases.logs.FailureLogUseCase;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
public abstract class AbstractCsvHandlerUseCase<I, T> implements CsvHandlerUseCase<I> {

    protected CsvHandlerUseCase<T> nextUseCase;
    private FailureLogUseCase failureLogsUseCase;

    public AbstractCsvHandlerUseCase(CsvHandlerUseCase<T> nextUseCase, FailureLogUseCase failureLogsUseCase) {
        this.nextUseCase = nextUseCase;
        this.failureLogsUseCase = failureLogsUseCase;
    }

    protected abstract T executeUseCase(I input, String processId);

    @SneakyThrows
    @Override
    public void run(I input, String processId) {

        try {
            T result = executeUseCase(input, processId);

            if (nextUseCase != null) {
                nextUseCase.run(result, processId);
            }
        } catch (Exception e) {
            FailureLogEntity entity = FailureLogEntity.builder()
                    .uuid(UUID.randomUUID().toString())
                    .processId(processId)
                    .log(e.getMessage())
                    .dateTime(LocalDateTime.now())
                    .build();
            failureLogsUseCase.saveLog(entity);
            log.error(String.valueOf(e));
        }
    }

    protected void logDebug(String message) {
        log.debug(String.format("[%s] %s", this.getClass().getSimpleName(), message));
    }
}
