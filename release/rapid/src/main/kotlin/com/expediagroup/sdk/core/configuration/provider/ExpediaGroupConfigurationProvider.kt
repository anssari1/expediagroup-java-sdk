/*
 * Copyright (C) 2022 Expedia, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.expediagroup.sdk.core.configuration.provider

import com.expediagroup.sdk.core.configuration.provider.ExpediaGroupConfigurationProvider.authEndpoint
import com.expediagroup.sdk.core.configuration.provider.ExpediaGroupConfigurationProvider.endpoint
import com.expediagroup.sdk.core.configuration.provider.ExpediaGroupConfigurationProvider.name
import com.expediagroup.sdk.core.configuration.provider.ExpediaGroupConfigurationProvider.requestTimeout

/**
 * Default configuration provider for ExpediaGroup.
 *
 * @property name The name of the provider.
 * @property endpoint The API endpoint to use for requests.
 * @property authEndpoint The API endpoint to use for authentication.
 * @property requestTimeout The API response timeout to use for requests.
 */
internal object ExpediaGroupConfigurationProvider : ConfigurationProvider {
    override val name: String = "ExpediaGroup Configuration Provider"
    override val endpoint: String = "https://api.expediagroup.com/"
    override val authEndpoint: String = "${endpoint}identity/oauth2/v2/token/"
}
