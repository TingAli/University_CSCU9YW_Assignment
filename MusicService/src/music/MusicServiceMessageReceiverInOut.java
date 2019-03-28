/**
 * MusicServiceMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.9  Built on : Nov 16, 2018 (12:05:37 GMT)
 */
package music;


/**
 *  MusicServiceMessageReceiverInOut message receiver
 */
public class MusicServiceMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver {
    public void invokeBusinessLogic(
        org.apache.axis2.context.MessageContext msgContext,
        org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault {
        try {
            // get the implementation class for the Web Service
            Object obj = getTheImplementationObject(msgContext);

            MusicServiceSkeleton skel = (MusicServiceSkeleton) obj;

            //Out Envelop
            org.apache.axiom.soap.SOAPEnvelope envelope = null;

            //Find the axisOperation that has been set by the Dispatch phase.
            org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext()
                                                                      .getAxisOperation();

            if (op == null) {
                throw new org.apache.axis2.AxisFault(
                    "Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
            }

            java.lang.String methodName;

            if ((op.getName() != null) &&
                    ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJavaIdentifier(
                            op.getName().getLocalPart())) != null)) {
                if ("getByComposer".equals(methodName)) {
                    music.Tracks tracks10 = null;
                    music.ComposerName wrappedParam = (music.ComposerName) fromOM(msgContext.getEnvelope()
                                                                                            .getBody()
                                                                                            .getFirstElement(),
                            music.ComposerName.class);

                    tracks10 = skel.getByComposer(wrappedParam);

                    envelope = toEnvelope(getSOAPFactory(msgContext), tracks10,
                            false,
                            new javax.xml.namespace.QName("urn:Music", "Tracks"));
                } else if ("getByDisc".equals(methodName)) {
                    music.Tracks tracks12 = null;
                    music.DiscNumber wrappedParam = (music.DiscNumber) fromOM(msgContext.getEnvelope()
                                                                                        .getBody()
                                                                                        .getFirstElement(),
                            music.DiscNumber.class);

                    tracks12 = skel.getByDisc(wrappedParam);

                    envelope = toEnvelope(getSOAPFactory(msgContext), tracks12,
                            false,
                            new javax.xml.namespace.QName("urn:Music", "Tracks"));
                } else {
                    throw new java.lang.RuntimeException("method not found");
                }

                newMsgContext.setEnvelope(envelope);
            }
        } catch (ErrorFault e) {
            msgContext.setProperty(org.apache.axis2.Constants.FAULT_NAME,
                "ErrorMessage");

            org.apache.axis2.AxisFault f = createAxisFault(e);

            if (e.getFaultMessage() != null) {
                f.setDetail(toOM(e.getFaultMessage(), false));
            }

            throw f;
        } catch (java.lang.Exception e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    //
    private org.apache.axiom.om.OMElement toOM(music.ComposerName param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(music.ComposerName.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(music.Tracks param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(music.Tracks.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(music.ErrorMessage param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(music.ErrorMessage.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(music.DiscNumber param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(music.DiscNumber.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory, music.Tracks param,
        boolean optimizeContent, javax.xml.namespace.QName elementQName)
        throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(music.Tracks.MY_QNAME,
                    factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private music.Tracks wrapGetByComposer() {
        music.Tracks wrappedElement = new music.Tracks();

        return wrappedElement;
    }

    private music.Tracks wrapGetByDisc() {
        music.Tracks wrappedElement = new music.Tracks();

        return wrappedElement;
    }

    /**
     *  get the default envelope
     */
    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory) {
        return factory.getDefaultEnvelope();
    }

    private java.lang.Object fromOM(org.apache.axiom.om.OMElement param,
        java.lang.Class type) throws org.apache.axis2.AxisFault {
        try {
            if (music.ComposerName.class.equals(type)) {
                return music.ComposerName.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (music.DiscNumber.class.equals(type)) {
                return music.DiscNumber.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (music.ErrorMessage.class.equals(type)) {
                return music.ErrorMessage.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (music.Tracks.class.equals(type)) {
                return music.Tracks.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }
        } catch (java.lang.Exception e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

        return null;
    }

    private org.apache.axis2.AxisFault createAxisFault(java.lang.Exception e) {
        org.apache.axis2.AxisFault f;
        Throwable cause = e.getCause();

        if (cause != null) {
            f = new org.apache.axis2.AxisFault(e.getMessage(), cause);
        } else {
            f = new org.apache.axis2.AxisFault(e.getMessage());
        }

        return f;
    }
} //end of class
