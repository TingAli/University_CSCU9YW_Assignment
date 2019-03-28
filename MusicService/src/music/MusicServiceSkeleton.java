/**
 * MusicServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.9  Built on : Nov 16, 2018 (12:05:37 GMT)
 */
package music;


/**
 *  MusicServiceSkeleton java skeleton for the axisService
 */
public class MusicServiceSkeleton {
    /**
     * Auto generated method signature
     * This operation takes part of the name for a
                                composer/artist, and returns a list of track details whose
                                composer/artist contains the given string. An &quot;errorFault&quot; is
                                thrown
                                if the name is empty or does not match.
     * @param composerName
     * @return tracks
     * @throws ErrorFault
     */
    public music.Tracks getByComposer(music.ComposerName composerName)
        throws ErrorFault {
        //TODO : fill this with the necessary business logic
        throw new java.lang.UnsupportedOperationException("Please implement " +
            this.getClass().getName() + "#getByComposer");
    }

    /**
     * Auto generated method signature
     * This operation takes the number of a disc, and returns
                                a list of track details for this disc. An &quot;errorFault&quot; is thrown if
                                the
                                disc number is invalid or does not match.
     * @param discNumber
     * @return tracks0
     * @throws ErrorFault
     */
    public music.Tracks getByDisc(music.DiscNumber discNumber)
        throws ErrorFault {
        //TODO : fill this with the necessary business logic
        throw new java.lang.UnsupportedOperationException("Please implement " +
            this.getClass().getName() + "#getByDisc");
    }
}
