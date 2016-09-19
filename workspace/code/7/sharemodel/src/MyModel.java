import javax.swing.DefaultBoundedRangeModel;

class MyModel extends DefaultBoundedRangeModel // implements BoundedRangeModel
{ // DefaultBoundedRangeModel(int value, int extent, int min, int max)
	public MyModel() {
		super(0, 5, 0, 100);
	}
};
