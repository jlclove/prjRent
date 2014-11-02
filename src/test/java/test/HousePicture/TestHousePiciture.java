package test.HousePicture;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dyrent.model.HousePicture;
import com.dyrent.service.HousePictureService;

import test.BaseTest;

public class TestHousePiciture extends BaseTest {

	@Autowired
	private HousePictureService housePictureService;

	@Test
	public void findListById() {
		System.out.println(housePictureService.getPictureListByLijikanId(2));
	}

	@Test
	public void batchInsert() {
		List<HousePicture> list = new ArrayList<HousePicture>();
		list.add(new HousePicture(
				2,
				"/fetch/vp/fy/gi/2013/0810/C152933BFEDD406981A46B7324352A78.JPG",
				"房型图", 3));
		list.add(new HousePicture(
				2,
				"/fetch/vp/fy/gi/2013/0810/682A02197F3546CD8BF57CAAABE1E133.JPG",
				"卧室", 7));
		list.add(new HousePicture(
				2,
				"/fetch/vp/fy/gi/2013/0810/7436A252255F4C8E90DECDAB8EC42A73.JPG",
				"卫生间", 1));
		System.out.println(housePictureService.insertBatchPicture(list));
	}
	
	@Test
	public void afa(){
		String a = "aaa\\dfdf";
		System.out.println(a);
		System.out.println(a.replaceAll("\\\\", "/"));
	}

}
