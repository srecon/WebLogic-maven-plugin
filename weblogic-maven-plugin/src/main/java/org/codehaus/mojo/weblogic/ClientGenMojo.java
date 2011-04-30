package org.codehaus.mojo.weblogic;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Path;
import org.codehaus.mojo.weblogic.util.WeblogicMojoUtilities;
import weblogic.wsee.tools.anttasks.ClientGenTask;

import java.io.File;

/**
 * @author: Recon
 * Date: Oct 31, 2009
 * Time: 5:05:09 PM
 * @version: 1
 */
public class ClientGenMojo extends AbstractWeblogicMojo{


    /**
     * The filename of the war file to find the services. The file path is
     * extracted from the artifact list.
     *
     * @parameter
     */
    private String warFileName;

    /**
     * The wsdl to client gen from. If warFileName is specified, this parameter
     * is the root relative file to use when creating the URI for the wsdl.
     *
     * @parameter
     */
    private String inputWSDL;

    /**
     * The directory to output the generated code to.
     *
     * @parameter default-value="${basedir}/src/main/java"
     */
    private String outputDir;

    /**
     * The package name of the output code.
     *
     * @parameter default-value="com.test.webservice"
     */
    private String packageName;

    /**
     * The name of the service.
     *
     * @parameter
     */
    private String serviceName;

    /**
     * Output verbose messages
     *
     * @parameter default-value="false"
     */
    private boolean verbose;

    /**
     * Whether or not to use server types from the ear file in the client jar.
     *
     * @parameter default-value="false"
     */
    private boolean useServerTypes;

    /**
     * Sets whether or not to create the type conversions for a web service in
     * the client.
     *
     * @parameter default-value="true"
     */
    private boolean autotype;

    /**
     * Sets whether or not to use the jaxRPCWrappedArrayStyle
     *
     * @parameter default-value="true"
     */
    private boolean jaxRPCWrappedArrayStyle;

    /**
     * This method will run client gen on the given WSDL.
     *
     * @throws org.apache.maven.plugin.MojoExecutionException Thrown if we fail to obtain the WSDL.
     */
    public void execute()
        throws MojoExecutionException
    {
        super.execute();

        if ( getLog().isInfoEnabled() )
        {
            getLog().info( "Weblogic client gen10 beginning " );
        }
        if ( getLog().isInfoEnabled() )
        {
            getLog().info( " Detailed client gen settings information " + this.toString() );
        }

        try
        {
            final ClientGenTask clientGen = new ClientGenTask();
            // Set the classpath
            final Project project = new Project();
            project.setName( "clientgen" );
            final Path path = new Path( project, WeblogicMojoUtilities
                .getDependencies( this.getArtifacts(), this.getPluginArtifacts() ) );
            clientGen.setProject( project );
            clientGen.setClasspath( path );
            //clientGen.setOverwrite( true );
            clientGen.setVerbose( this.verbose );
            clientGen.setDestDir( new File( this.outputDir ) );
            clientGen.setPackageName( this.packageName );
            clientGen.setIncludeGlobalTypes( this.useServerTypes );
            clientGen.setJaxRPCWrappedArrayStyle( this.jaxRPCWrappedArrayStyle );
            String wsdlUri;
            if ( this.warFileName != null )
            {
                if ( getLog().isInfoEnabled() )
                {
                    getLog().info(
                        " calculating wsdl URI from warFileName " + this.warFileName + " with wsdl " + this.inputWSDL );
                }
                wsdlUri = "jar:file:" + WeblogicMojoUtilities.getWarFileName( this.getArtifacts(), this.warFileName ) +
                    "!" + this.inputWSDL;
                new File( this.inputWSDL ).toURI().toString();
                if ( getLog().isInfoEnabled() )
                {
                    getLog().info( " using " + wsdlUri + " for clientgen." );
                }
            }
            else if ( this.inputWSDL.startsWith( "http" ) )
            {
                if ( getLog().isInfoEnabled() )
                {
                    getLog().info( " using " + this.inputWSDL + " for clientgen." );
                }
                wsdlUri = this.inputWSDL;
            }
            else
            {
                wsdlUri = new File( this.inputWSDL ).toURI().toString();
                if ( getLog().isInfoEnabled() )
                {
                    getLog().info( " using " + wsdlUri + " for clientgen." );
                }
            }
            clientGen.setWsdl( wsdlUri );
            // set the service name if it is specified
            if ( this.serviceName != null )
            {
                if ( getLog().isInfoEnabled() )
                {
                    getLog().info( " generating client for service '" + this.serviceName + "'." );
                }
                clientGen.setServiceName( this.serviceName );
            }
            clientGen.execute();
        }
        catch ( Exception ex )
        {
            getLog().error( "Exception encountered during client gen", ex );
            throw new MojoExecutionException( "Exception encountered during listapps", ex );
        }
        finally{
            //WeblogicMojoUtilities.un
        }
        if ( getLog().isInfoEnabled() )
        {
            getLog().info( "Weblogic client gen 10 successful " );
        }
    }

    /**
     * Getter for property input WSDL.
     *
     * @return The value of input WSDL.
     */
    public String getInputWSDL()
    {
        return this.inputWSDL;
    }

    /**
     * Setter for the input WSDL.
     *
     * @param inInputWSDL The value of input WSDL.
     */
    public void setInputWSDL( final String inInputWSDL )
    {
        this.inputWSDL = inInputWSDL;
    }

    /**
     * Getter for property output dir.
     *
     * @return The value of output dir.
     */
    public String getOutputDir()
    {
        return this.outputDir;
    }

    /**
     * Setter for the output dir.
     *
     * @param inOutputDir The value of output dir.
     */
    public void setOutputDir( final String inOutputDir )
    {
        this.outputDir = inOutputDir;
    }

    /**
     * Getter for property package name.
     *
     * @return The value of package name.
     */
    public String getPackageName()
    {
        return this.packageName;
    }

    /**
     * Setter for the package name.
     *
     * @param inPackageName The value of package name.
     */
    public void setPackageName( String inPackageName )
    {
        this.packageName = inPackageName;
    }

    /**
     * Getter for property service name.
     *
     * @return The value of service name.
     */
    public String getServiceName()
    {
        return this.serviceName;
    }

    /**
     * Setter for the service name.
     *
     * @param inServiceName The value of service name.
     */
    public void setServiceName( final String inServiceName )
    {
        this.serviceName = inServiceName;
    }

    /**
     * toString method: creates a String representation of the object
     *
     * @return the String representation
     */
    public String toString()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append( "ClientGen9Mojo[" );
        buffer.append( "inputWSDL = " ).append( inputWSDL );
        buffer.append( ", outputDir = " ).append( outputDir );
        buffer.append( ", packageName = " ).append( packageName );
        buffer.append( ", serviceName = " ).append( serviceName );
        buffer.append( ", useServerTypes = " ).append( useServerTypes );
        buffer.append( ", autotype = " ).append( autotype );
        buffer.append( "]" );
        return buffer.toString();
    }

    /**
     * Getter for server types
     *
     * @return true if the client gen should use server type information
     */
    public boolean isUseServerTypes()
    {
        return useServerTypes;
    }

    /**
     * Setter for server types
     *
     * @param useServerTypes - true if the client gen should use server types
     */
    public void setUseServerTypes( boolean useServerTypes )
    {
        this.useServerTypes = useServerTypes;
    }

    /**
     * Getter for verbose messages
     *
     * @return true if the client gen should use verbose output
     */
    public boolean isVerbose()
    {
        return this.verbose;
    }

    /**
     * Setter for verbose messages
     *
     * @param verbose - true of the clientgen should use verbose output
     */
    public void setVerbose( boolean verbose )
    {
        this.verbose = verbose;
    }


    /**
     * Getter for autoType
     *
     * @return true if clientgen shoud autotype from the wsdl
     */
    public boolean isAutotype()
    {
        return this.autotype;
    }

    /**
     * Setter for autoType
     *
     * @param autotype - true if the client should autotype
     */
    public void setAutotype( boolean autotype )
    {
        this.autotype = autotype;
    }

    /**
     * Getter for warFileName
     *
     * @return the warFileName
     */
    public String getWarFileName()
    {
        return this.warFileName;
    }

    /**
     * Setter for warFileName
     *
     * @param warFileName - the warFileName to set
     */
    public void setWarFileName( String warFileName )
    {
        this.warFileName = warFileName;
    }

    /**
     * Getter for jaxRPCWrappedArrayStyle
     *
     * @return the jaxRPCWrappedArrayStyle
     */
    public boolean isJaxRPCWrappedArrayStyle()
    {
        return jaxRPCWrappedArrayStyle;
    }

    /**
     * Setter for jaxRPCWrappedArrayStyle
     *
     * @param jaxRPCWrappedArrayStyle the jaxRPCWrappedArrayStyle to set
     */
    public void setJaxRPCWrappedArrayStyle( boolean jaxRPCWrappedArrayStyle )
    {
        this.jaxRPCWrappedArrayStyle = jaxRPCWrappedArrayStyle;
    }    
}
