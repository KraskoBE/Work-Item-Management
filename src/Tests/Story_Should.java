package Tests;

import com.company.models.common.Priority;
import com.company.models.common.Size;
import com.company.models.workItem.StoryImpl;
import org.junit.Test;

public class Story_Should {
    @Test
    public void createStoryWhenValidParametersArePassed() {
        StoryImpl story = new StoryImpl(0, "titleOfStory", "description of story", Priority.High, Size.Large);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenNameIsSmallerThanValue() {
        StoryImpl story = new StoryImpl(0, "title", "description of story", Priority.High, Size.Large);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenNameIsLargerThanValue() {
        StoryImpl story = new StoryImpl(0, "tooLooooooooooooooooooooooooooooooooooooooooooooong", "description of story", Priority.High, Size.Large);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwWhenDescriptionIsSmallerThanValue() {
        StoryImpl story = new StoryImpl(0, "titleOfStory", "descr", Priority.High, Size.Large);
    }


    @Test(expected = IllegalArgumentException.class)
    public void throwWhenDescriptionIsLargerThanValue() {
        StringBuilder description = new StringBuilder();
        for (int i = 0; i < 501; i++)
            description.append("a");

        StoryImpl story = new StoryImpl(0, "titleOfStory", description.toString(), Priority.High, Size.Large);
    }
}
