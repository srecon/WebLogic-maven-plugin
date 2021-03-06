package org.codehaus.mojo.weblogic;

/*
 * Copyright 2008 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * This class represents a service to be generated by the JwscMojo.
 *
 * @author <a href="mailto:josborn@belltracy.com">Jon Osborn</a>
 * @see org.codehaus.mojo.weblogic.JwscMojo
 */
public class Service
{

    /**
     * The ejb jar file name
     *
     * @required
     */
    private String ejbJar;

    /**
     * Should methods be expanded
     *
     * @parameter
     */
    private boolean expandMethods = true;

    /**
     * Should types be generated
     *
     * @parameter
     */
    private boolean generateTypes = true;

    /**
     * list of ejbs to generate
     *
     * @required
     */
    private String includeEJBs;

    /**
     * The target namespace for the service
     *
     * @parameter
     * @required
     */
    private String targetNamespace;

    /**
     * The service name to generate
     *
     * @parameter
     * @required
     */
    private String serviceName;

    /**
     * The service uri to generate the service to
     *
     * @parameter
     * @required
     */
    private String serviceUri;

    public String getServiceUri()
    {
        return serviceUri;
    }

    public void setServiceUri( String serviceUri )
    {
        this.serviceUri = serviceUri;
    }

    public String getServiceName()
    {
        return serviceName;
    }

    public void setServiceName( String serviceName )
    {
        this.serviceName = serviceName;
    }

    public Service()
    {
        super();
    }

    public String getEjbJar()
    {
        return ejbJar;
    }

    public String getIncludeEJBs()
    {
        return includeEJBs;
    }

    public String getTargetNamespace()
    {
        return targetNamespace;
    }

    public boolean isExpandMethods()
    {
        return expandMethods;
    }

    public boolean isGenerateTypes()
    {
        return generateTypes;
    }

    public void setEjbJar( String ejbJar )
    {
        this.ejbJar = ejbJar;
    }

    public void setExpandMethods( boolean expandMethods )
    {
        this.expandMethods = expandMethods;
    }

    public void setGenerateTypes( boolean generateTypes )
    {
        this.generateTypes = generateTypes;
    }

    public void setIncludeEJBs( String includeEJBs )
    {
        this.includeEJBs = includeEJBs;
    }

    public void setTargetNamespace( String targetNamespace )
    {
        this.targetNamespace = targetNamespace;
    }

}
