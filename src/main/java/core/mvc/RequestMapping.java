package core.mvc;

import java.util.HashMap;
import java.util.Map;

import next.controller.AddCommentController;
import next.controller.CreateQuestionController;
import next.controller.ListController;
import next.controller.ShowController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestMapping {
	private static final Logger logger = LoggerFactory.getLogger(FrontController.class);
	private Map<String, Controller> mappings = new HashMap<String, Controller>();
	
	public void initMapping() {
		System.out.println("maping");
		mappings.put("/list.next", new ListController());
		mappings.put("/show.next", new ShowController());
		mappings.put("/api/addanswer.next", new AddCommentController());
		mappings.put("/save.next", new CreateQuestionController());
		mappings.put("/form.next", new ForwardController("form.jsp"));
		
		logger.info("Initialized Mapping Completed!");
	}

	public Controller findController(String url) {
		return mappings.get(url);
	}

	void put(String url, Controller controller) {
		mappings.put(url, controller);
	}

}
