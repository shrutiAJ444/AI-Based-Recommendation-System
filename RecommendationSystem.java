import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import java.io.File;
import java.util.List;

public class RecommendationSystem {
    public static void main(String[] args) {
        try {
            // Load sample data
            DataModel model = new FileDataModel(new File("C:/Users/admin/Desktop/practice/data.csv"));

            
            // Define similarity algorithm
            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
            
            // Define user neighborhood
            UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);
            
            // Create the recommender system
            Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
            
            // Generate recommendations for a user (user ID = 1)
            List<RecommendedItem> recommendations = recommender.recommend(1, 3);
            
            // Print recommendations
            for (RecommendedItem recommendation : recommendations) {
                System.out.println("Recommended Item: " + recommendation.getItemID() + " with value: " + recommendation.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
