/*
 * Student Number: 2410516
 */
package music;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MusicService extends MusicServiceSkeleton {

	private final static String databaseHost = "mysql0.cs.stir.ac.uk";
	private final static String databaseName = "CSCU9YW";
	private final static String databasePassword = "Password!123";
	private final static String databaseUser = "ial";
	private final static String discTable = "music";

	/***
	 * Get tracks by composer name.
	 * 
	 * @param trackDetail
	 *            with composerName filled in.
	 * @return tracks relevant to what has been searched for.
	 */
	public Tracks getByComposer(ComposerName composerName) throws ErrorFault {
		String fieldName = "composer";
		String value = composerName.getComposerName();

		if (value != null && !value.isEmpty() && !value.trim().isEmpty()) {
			try {
				TrackDetail[] trackDetails = getByField(fieldName, value, false);
				TrackDetails trackDetailsList = new TrackDetails();
				trackDetailsList.setTrackDetail(trackDetails);
				Tracks tracks = new Tracks();

				tracks.setTracks(trackDetailsList);

				return tracks;
			} catch (Exception ex) {
				throw ex;
			}
		} else {
			Exception exception = new Exception("ComposerName must not be empty!");
			String errorMessage = "Request model is malformed - " + exception.getMessage();
			throw (new ErrorFault(errorMessage, exception));
		}
	}

	/***
	 * Get tracks by disc number.
	 * 
	 * @param trackDetail
	 *            with composerName filled in.
	 * @return tracks relevant to what has been searched for.
	 */
	public Tracks getByDisc(DiscNumber discNumber) throws ErrorFault {
		String fieldName = "disc";
		int value = discNumber.getDiscNumber();

		if (value >= 0) {
			try {
				TrackDetail[] trackDetails = getByField(fieldName, Integer.toString(value), true);
				TrackDetails trackDetailsList = new TrackDetails();
				trackDetailsList.setTrackDetail(trackDetails);
				Tracks tracks = new Tracks();

				tracks.setTracks(trackDetailsList);

				return tracks;
			} catch (NumberFormatException ex) {
				throw (new ErrorFault("DiscNumber must not be empty and must be an integer greater than or equal to 0!",
						ex));
			} catch (Exception ex) {
				throw ex;
			}
		} else {
			Exception exception = new Exception(
					"DiscNumber must not be empty and must be an integer greater than or equal to 0!");
			String errorMessage = "Request model is malformed - " + exception.getMessage();
			throw (new ErrorFault(errorMessage, exception));
		}
	}

	/**
	 * Get value from database, based on field name being the column name and
	 * the value.
	 * 
	 * @param field
	 *            name to be equal to the column name from the database
	 * @param value
	 *            of the field value from the database
	 * @param isExactMatch
	 *            should be set to false if the value in the database should
	 *            contain the value parameter
	 * @return array of TrackDetail objects
	 * @throws ErrorFault
	 *             if exception occurs
	 */
	private TrackDetail[] getByField(String field, String value, boolean isExactMatch) throws ErrorFault {
		try {
			// Check field and value are not empty
			if (field.length() == 0)
				throw (new Exception("field is empty"));
			if (value.length() == 0)
				throw (new Exception("value is empty"));
			// Form a database connection
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String databaseDesignation = "jdbc:mysql://" + databaseHost + "/" + databaseName + "?user=" + databaseUser
					+ "&password=" + databasePassword;
			Connection connection = DriverManager.getConnection(databaseDesignation);
			Statement statement = connection.createStatement();
			// Query to find non-exact match
			String query = "SELECT id, disc, track, composer, work, title " + "FROM " + discTable + " " + "WHERE "
					+ field + " LIKE '%" + value + "%'";
			// Query to find exact match
			if (isExactMatch) {
				query = "SELECT id, disc, track, composer, work, title " + "FROM " + discTable + " " + "WHERE " + field
						+ " = '" + value + "'";
			}
			ResultSet result = statement.executeQuery(query);
			result.last();
			int resultCount = result.getRow();
			// Throw exception if result is not found
			if (resultCount == 0)
				throw (new Exception(field + " '" + value + "' not found"));

			TrackDetail[] trackDetails = new TrackDetail[resultCount];
			result.beforeFirst();
			int resultIndex = 0;
			while (result.next()) {
				TrackDetail trackDetail = new TrackDetail();

				// Id
				trackDetail.setId(result.getInt(1));
				// Disc number
				trackDetail.setDiscNumber(result.getInt(2));
				// Track number
				trackDetail.setTrackNumber(result.getInt(3));
				// Composer name
				trackDetail.setComposerName(result.getString(4));
				// Work name
				trackDetail.setWorkName(result.getString(5));
				// Title name
				trackDetail.setTitleName(result.getString(6));

				// Add the TrackDetail to the list of TrackDetail objects
				trackDetails[resultIndex++] = trackDetail;
			}

			connection.close();

			return (trackDetails);
		} catch (Exception exception) {
			String errorMessage = "database access error - " + exception.getMessage();
			throw (new ErrorFault(errorMessage, exception));
		}
	}
}
