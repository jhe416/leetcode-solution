package leetcode.conquer.sol.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * the idea of this solution is based on finding the circle of the course
 * use an arr to track the status 1 being visited and 2 being visiting.
 * if a prerequisite course happen to have a prerequisite course that is is a visiting state
 * this is a circle. meaning a course has a prerequisite and the prerequisite has its prerequisite to the course.
 * will return false.
 * if no circle found we return true.
 * a detailed explanation can be found in this link below:
 * https://www.youtube.com/watch?v=M6SBePBMznU
 * Time O(n) since we flip the visted node to viste(status 1)
 * Space O(n) we need to store all the prerequisites
 * 
 * this solution can also be done through a hash map solution attached
 */
public class CourseSchedule {
	 public CourseSchedule() {}
	 
	 //since course number given are 0 to n-1 we call use arr index as a map key, need to initialize
	 public boolean canFinish(int numCourses, int[][] prerequisites) {
	        List<List<Integer>> table = new ArrayList<>();
	        for(int i=0; i<numCourses;i++){
	            table.add(new ArrayList<>());
	        }
	        
	        for(int i=0; i<prerequisites.length;i++){            
	            table.get(prerequisites[i][0]).add(prerequisites[i][1]);
	        }
	        
	        int[] v = new int[numCourses];
	        for(int i=0;i<numCourses;i++){
	            if(dfs(table,v,i)) return false;
	        }
	        
	        return true;
	    }
	    
	    private boolean dfs(List<List<Integer>> table, int[] v, Integer course){
	        //visited
	        if(v[course] == 1) return false;
	        //visiting
	        if(v[course] == 2) return true;
	        
	        v[course] = 2;
	        for(int i : table.get(course)){
	            if(dfs(table,v,i)) return true;
	        }
	        
	        v[course] = 1;
	        return false; 
	    }
	    
	    public boolean canFinishMySol(int numCourses, int[][] prerequisites) {
	        boolean[] visit = new boolean[numCourses];
	        boolean[] visited = new boolean[numCourses];
	        Map<Integer,List<Integer>> map = new HashMap<>();
	        for(int[] pair : prerequisites){
	            List<Integer> list = map.getOrDefault(pair[0],new ArrayList<>());
	            list.add(pair[1]);
	            map.putIfAbsent(pair[0],list);
	        }
	        
	        for(int i=0;i<numCourses;i++){
	            if(!helper(visit, visited, map,i)) return false;
	        }
	        return true;
	    }
	    
	    private boolean helper(boolean[] visit, boolean[] visited, Map<Integer,List<Integer>> map, int curr){
	        if(visit[curr]) return false;
	        if(visited[curr]) return true;
	        
	        visit[curr] = true;
	        List<Integer> list = map.getOrDefault(curr, new ArrayList<>());
	        for(int val : list){
	            if(!helper(visit,visited,map,val)) return false;
	        }
	        visit[curr] = false;
	        visited[curr] = true;
	        return true;
	    }
}
