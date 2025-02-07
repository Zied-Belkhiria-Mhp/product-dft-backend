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
 */

package com.catenax.dft.mapper;

import com.catenax.dft.entities.database.ProcessReportEntity;
import com.catenax.dft.entities.usecases.ProcessReport;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Mapper(componentModel = "spring")
public interface ProcessReportMapper {

    ProcessReportEntity mapFrom(ProcessReport processReport);

    ProcessReport mapFrom(ProcessReportEntity processReportEntity);
}
