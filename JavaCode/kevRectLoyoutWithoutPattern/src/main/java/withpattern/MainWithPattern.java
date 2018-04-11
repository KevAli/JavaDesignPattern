package withpattern;

import withpattern.creator.RectModuleConcreteCreator;
import withpattern.creator.RectModuleCreator;
import withpattern.creator.RectWorkspaceConcreteCreator;
import withpattern.creator.RectWorkspaceCreator;
import withpattern.product.RectModule;
import withpattern.product.RectWorkspace;
import withpattern.strategy.layout.LoyoutWithLowLine;
import withpattern.tools.lowline.LowLineList;

import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainWithPattern {
    public static void main(String args[]) {
        RectWorkspace rectWorkspace = new RectWorkspace();
        RectWorkspaceCreator rectWorkspaceCreator = new RectWorkspaceConcreteCreator();
        RectModuleCreator rectModuleCreator = new RectModuleConcreteCreator();
        if (args.length != 2) {
            System.out.println("Please Input 2 Params <InputPath> <OutputPath>");
        }
        File file = new File(args[0]);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                System.out.println("line " + line + ": " + tempString);
                //第一行是工作台信息，往后是矩形件信息
                if (line == 1) {
                    String[] workspaceParams = tempString.split("-");
                    System.out.println(workspaceParams.length + " " + workspaceParams[0] + " " + workspaceParams[1]);
                    rectWorkspace = rectWorkspaceCreator.getRectangle();
                    rectWorkspace.setProperty("Workspace", Integer.parseInt(workspaceParams[0]), Integer.parseInt(workspaceParams[1]));
                    System.out.println(rectWorkspace);
                } else {
                    String[] RectModuleParams = tempString.split("-");
                    for (int i = 0; i < Integer.parseInt(RectModuleParams[4]); i++) {
                        //RectModuleConcreteCreator rectModuleConcreteCreator = new RectModuleConcreteCreator();
                        RectModule rectModule = rectModuleCreator.getRectangle();
                        rectModule.setProperty(RectModuleParams[0], Integer.parseInt(RectModuleParams[1]),
                                Integer.parseInt(RectModuleParams[2]), Integer.parseInt(RectModuleParams[3]));
                        rectWorkspace.getMdls().add(rectModule);
                        System.out.println("RectModule " + i + ": " + rectModule);
                    }
                }
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        System.out.println("Before Sort-------------------------------------------------------------------------------------");
        for (RectModule rectModule : rectWorkspace.getMdls()) {
            System.out.println(rectModule);
        }
        System.out.println("After Sort-------------------------------------------------------------------------------------");
//        rectWorkspace.sortByValue();
        Collections.sort(rectWorkspace.getMdls(), new Comparator<RectModule>() {
            public int compare(RectModule o1, RectModule o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        for (RectModule rectModule : rectWorkspace.getMdls()) {
            System.out.println(rectModule);
        }
        System.out.println("After LayoutStrategy-------------------------------------------------------------------------------------");
        //rectWorkspace.layoutWithLowLine();
        rectWorkspace.setLayoutStrategy(new LoyoutWithLowLine());
        //rectWorkspace.getLayoutStrategy().layout(rectWorkspace, rectWorkspace.getLowlinelist());
        LowLineList lowLineList = new LowLineList();
        lowLineList.setGuard(rectWorkspace.getWidth(), rectWorkspace.getHeight());
        rectWorkspace.setMdlsch(rectWorkspace.getLayoutStrategy().layout(rectWorkspace.getWidth(), rectWorkspace.getHeight(),
                rectWorkspace.getMdls(), lowLineList));
        int i = 1;
        for (List<RectModule> listRectModules : rectWorkspace.getMdlsch()) {
            System.out.printf("The %s Mdlsch:--------\n", i++);
            for (RectModule rectModule : listRectModules) {
                System.out.println(rectModule);
            }
        }
        try {
            FileWriter fw = new FileWriter(args[1]);
            int j = 1;
            for (List<RectModule> listRectModules : rectWorkspace.getMdlsch()) {
                System.out.printf("The %s Mdlsch:--------\n", j);
                fw.write("The " + j + "Mdlsch:--------\n");
                for (RectModule rectModule : listRectModules) {
                    System.out.println(rectModule);
                    fw.write(rectModule.toString() + "\n");
                }
                j += 1;
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
