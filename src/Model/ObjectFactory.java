package Model;

public class ObjectFactory {
	  public static GameObject getGameObject(String criteria, int x, int y)
	  {
	    if ( criteria.equals("Apple") )
	      return new Apple(x,y);
	    else if ( criteria.equals("Banana") )
	      return new Banana(x,y);
	    else if ( criteria.equals("Pear") )
	      return new Pear(x,y);
	    else if ( criteria.equals("Mice") )
		      return new Pear(x,y);
	    else if ( criteria.equals("Obstacle") )
		      return new Obstacle(x,y);
	    else if ( criteria.equals("Question") )
		      return new QuestionObject(x,y);

	    return null;
	  }
	}

