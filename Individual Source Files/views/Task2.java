package robot.views;

import robot.Robot;
import robot.RobotModel;
import robot.RobotModelEvent;
import robot.RobotModelListener;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.List;

public class Task2 extends Task1 implements  RobotModelListener {
    private List<TreeModelListener> _treeModelListeners = new ArrayList<TreeModelListener>();

    public Task2(RobotModel _robotModel) {
        super(_robotModel);
    }


    @Override
    public void update(RobotModelEvent event) {
        if(event.eventType()==RobotModelEvent.EventType.RobotAdded){
            int[] childIndices={event.index()};
            Object[] children={event.operand()};
            Robot parentBot= event.parent();
            Object[] path= parentBot.path().toArray();
            TreeModelEvent addRobot= new TreeModelEvent(event.source(),path,childIndices,children);

            for(TreeModelListener counter:_treeModelListeners){
                counter.treeNodesInserted(addRobot);
            }

        }else if(event.eventType()==RobotModelEvent.EventType.RobotRemoved){
            int[] childIndices={event.index()};
            Object[] children={event.operand()};
            TreePath path= new TreePath(event.parent());
            TreeModelEvent addRobot= new TreeModelEvent(event.source(),path,childIndices,children);


            for(TreeModelListener counter:_treeModelListeners){
                counter.treeNodesRemoved(addRobot);
            }
        }
        }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
        _treeModelListeners.add(l);
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        _treeModelListeners.remove(l);
    }

    public List<TreeModelListener> getTreeModelListeners() {
        return _treeModelListeners;
    }
}
