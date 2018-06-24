package robot.views;

import robot.*;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.List;

public class Task1 implements TreeModel {
    private RobotModel _adaptee;


    public Task1(RobotModel adaptee){
        _adaptee=adaptee;
    }


    @Override
    public Object getRoot() {
        return _adaptee.root();
    }

    @Override
    public Object getChild(Object parent, int index) {
        if(parent instanceof CarrierRobot){
            try{
                Robot robot=((CarrierRobot) parent).robotAt(index);
                return robot;
            }catch(IndexOutOfBoundsException e){
                return null;
            }
        }
        return null;
    }

    @Override
    public int getChildCount(Object parent) {
        if(parent instanceof CarrierRobot){
            if(((CarrierRobot) parent).robotCount()>0){
                return ((CarrierRobot) parent).robotCount();
            }
        }
        return 0;
    }

    @Override
    public boolean isLeaf(Object node) {
        if(node instanceof CarrierRobot){
            return false;
        }else if(node instanceof Robot){
            if(((Robot) node).parent() != null){
                return true;
            }
        }
        return false;

    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {

    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        if(parent instanceof CarrierRobot){
            return ((CarrierRobot) parent).indexOf(((Robot)child));
        }else if(parent == null || child == null){
            return -1;
        }
        return -1;
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        throw new UnsupportedOperationException();
    }


}
