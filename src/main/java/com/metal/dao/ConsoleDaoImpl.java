package com.metal.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.metal.common.Constants;
import com.metal.model.SubTask;
import com.metal.model.SubVideoTaskBean;
import com.metal.model.Task;
import com.metal.model.VideoTaskBean;
import com.mysql.jdbc.Statement;

@Component
public class ConsoleDaoImpl implements ConsoleDao {

	private final JdbcTemplate jdbcTemplate;

	private static final String TV_SHOW_INSERT_SQL = "insert into tv_show (tv_show_name) values (?)";
	
	private static final String VIDEO_TASK_INSERT_SQL = "insert into video_task (url,platform,title,status,reset_time,tv_id) values (?,?,?,?,?,?)";
	
	private static final String QUERY_VIDEO_TASK = "select vid,url,platform,title,status,start_time,end_time,reset_time from video_task order by start_time desc limit 100";
	
	private static final String QUERY_VIDEO_TASK_BY_ID = "select vid,url,platform,title,status,start_time,end_time,reset_time from video_task where vid=?";
	
	private static final String QUERY_SUB_VIDEO_TASK_BY_ID = "select sub_vid,vid,page_url,platform,title,status,add_time,last_update_time from sub_video_task where sub_vid=?";
	
	private static final String UPDATE_VIDEO_TASK = "update video_task set title=?,reset_time=? where vid=?";
	
	private static final String UPDATE_VIDEO_TASK_STATUS = "update video_task set status=? where vid=?";
	
	private static final String UPDATE_SUB_VIDEO_TASK_STATUS = "update sub_video_task set status=? where sub_vid=?";
	
//	private static final String QUERY_SUB_TASK = "select sub_vid,vid,page_url,platform,title,status,add_time,last_update_time from sub_video_task";
	
	private static final String QUERY_SUB_VIDEO_TASK_BY_VID = "select sub_vid,vid,page_url,platform,title,status,add_time,last_update_time from sub_video_task where vid=? order by sub_vid asc";

	private static final String STOP_SUB_VIDEO_TASK = "update sub_video_task set status=" + Constants.TASK_STATUS_STOP + " where vid=? and status=" + Constants.TASK_STATUS_INIT;
	
	private static final String QUERY_TASK_SQL = "select task_id,key_word,status,start_time,end_time from task order by start_time desc limit 100";
	
	private static final String QUERY_SUB_TASK_SQL = "select sub_task_id,task_id,platform,url,status,start_time,end_time from sub_task where task_id=? order by sub_task_id desc limit 100";
	
	private static final String QUERY_TASK_BY_ID = "select task_id,key_word,status,start_time,end_time from task where task_id=?";
	
	private static final String QUERY_SUB_TASK_BY_ID = "select sub_task_id,task_id,platform,url,status,start_time,end_time from sub_task where sub_task_id=?";
	
	private static final String TASK_INSERT_SQL = "insert into task(key_word) values(?)";
	
	private static final String UPDATE_TASK_STATUS = "update task set status=? where task_id=?";
	
	private static final String UPDATE_SUB_TASK_STATUS = "update sub_task set status=? where sub_task_id=?";
	
	private static final String STOP_SUB_TASK = "update sub_task set status=" + Constants.TASK_STATUS_STOP + " where task_id=? and status=" + Constants.TASK_STATUS_INIT;
	
	@Autowired
	public ConsoleDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<VideoTaskBean> getVideoTasks() {
		List<VideoTaskBean> videoTasks = jdbcTemplate.query(QUERY_VIDEO_TASK, new RowMapper<VideoTaskBean>() {
			@Override
			public VideoTaskBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				return videoTaskPackage(rs);
			}
		});
		return videoTasks;
	}

	@Override
	public List<SubVideoTaskBean> getSubVideoTasks(long vid) {
		List<SubVideoTaskBean> subVideoTasks = jdbcTemplate.query(QUERY_SUB_VIDEO_TASK_BY_VID, new Object[] { vid }, new RowMapper<SubVideoTaskBean>() {
			@Override
			public SubVideoTaskBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				return subVideoTaskPackage(rs);
			}
		});
		return subVideoTasks;
	}

	@Override
	public long createTVShow(String title) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		jdbcTemplate.update(new PreparedStatementCreator() {  
			@Override
			public PreparedStatement createPreparedStatement(
					java.sql.Connection con) throws SQLException {
				   PreparedStatement ps = con.prepareStatement(TV_SHOW_INSERT_SQL, Statement.RETURN_GENERATED_KEYS); 
	               ps.setString(1, title);  
	               return ps;  
			}  
	    }, keyHolder);
		return keyHolder.getKey().longValue();
	}

	@Override
	public void createVideoTasks(VideoTaskBean videoTaskBean) {
		jdbcTemplate.update(VIDEO_TASK_INSERT_SQL, videoTaskBean.getUrl(), videoTaskBean.getPlatform(), videoTaskBean.getTitle(), Constants.TASK_STATUS_INIT, videoTaskBean.getReset_time(), videoTaskBean.getTv_id());
	}
	
	@Override
	public VideoTaskBean getVideoTaskById(long vid) {
		VideoTaskBean videoTask = jdbcTemplate.query(QUERY_VIDEO_TASK_BY_ID, new Object[] { vid }, new ResultSetExtractor<VideoTaskBean>() {
			@Override
			public VideoTaskBean extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if(rs.next()) {
					return videoTaskPackage(rs);
				} else {
					return null;
				}
			}
		});
		return videoTask;
	}

	@Override
	public SubVideoTaskBean getSubVideoTaskById(long subVid) {
		SubVideoTaskBean subVideoTask = jdbcTemplate.query(QUERY_SUB_VIDEO_TASK_BY_ID, new Object[] { subVid }, new ResultSetExtractor<SubVideoTaskBean>() {
			@Override
			public SubVideoTaskBean extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if(rs.next()) {
					return subVideoTaskPackage(rs);
				} else {
					return null;
				}
			}
			
		});
		return subVideoTask;
	}

	@Override
	public void revertVideoTask(long vid) {
		jdbcTemplate.update(UPDATE_VIDEO_TASK_STATUS, Constants.TASK_STATUS_INIT, vid);
	}
	
	@Override
	public void revertSubVideoTask(long subVid) {
		SubVideoTaskBean subVideoTask = jdbcTemplate.query(QUERY_SUB_VIDEO_TASK_BY_ID, new Object[] { subVid }, new ResultSetExtractor<SubVideoTaskBean>() {
			@Override
			public SubVideoTaskBean extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if(rs.next()) {
					return subVideoTaskPackage(rs);
				} else {
					return null;
				}
			}
			
		});
		jdbcTemplate.update(UPDATE_SUB_VIDEO_TASK_STATUS, Constants.TASK_STATUS_INIT, subVid);
		jdbcTemplate.update(UPDATE_VIDEO_TASK_STATUS, Constants.TASK_STATUS_RUNNING, subVideoTask.getVid());
	}
	
	@Override
	public void stopVideoTask(long vid) {
		jdbcTemplate.update(UPDATE_VIDEO_TASK_STATUS, Constants.TASK_STATUS_STOP, vid);
		jdbcTemplate.update(STOP_SUB_VIDEO_TASK, vid);
	}

	@Override
	public int getVideoCommentCount(long vid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSubVideoCommentCount(long subVid) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void updateVideoTask(VideoTaskBean video) {
		jdbcTemplate.update(UPDATE_VIDEO_TASK, video.getTitle(), video.getReset_time(), video.getVid());
	}

	@Override
	public List<Task> getTasks() {
		List<Task> tasks = jdbcTemplate.query(QUERY_TASK_SQL, new RowMapper<Task>() {
			@Override
			public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
				return taskPackage(rs);
			}
		});
		return tasks;
	}

	@Override
	public List<SubTask> getSubTasks(long taskId) {
		List<SubTask> subTasks = jdbcTemplate.query(QUERY_SUB_TASK_SQL, new Object[] { taskId }, new RowMapper<SubTask>() {
			@Override
			public SubTask mapRow(ResultSet rs, int rowNum) throws SQLException {
				return subTaskPackage(rs);
			}
		});
		return subTasks;
	}

	@Override
	public Task getTaskById(long task_id) {
		Task task = jdbcTemplate.query(QUERY_TASK_BY_ID, new Object[] { task_id }, new ResultSetExtractor<Task>() {
			@Override
			public Task extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if(rs.next()) {
					return taskPackage(rs);
				} else {
					return null;
				}
			}
		});
		return task;
	}

	@Override
	public SubTask getSubTaskById(long sub_task_id) {
		SubTask subTask = jdbcTemplate.query(QUERY_SUB_TASK_BY_ID, new Object[] { sub_task_id }, new ResultSetExtractor<SubTask>() {
			@Override
			public SubTask extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if(rs.next()) {
					return subTaskPackage(rs);
				} else {
					return null;
				}
			}
			
		});
		return subTask;
	}
	
	@Override
	public void createTask(Task task) {
		jdbcTemplate.update(TASK_INSERT_SQL, task.getKey_word());		
	}
	
	@Override
	public void revertTask(long task_id) {
		jdbcTemplate.update(UPDATE_TASK_STATUS, Constants.TASK_STATUS_INIT, task_id);
	}
	
	@Override
	public void revertSubTask(long subTaskId) {
		SubTask subTask = jdbcTemplate.query(QUERY_SUB_TASK_SQL, new Object[] { subTaskId }, new ResultSetExtractor<SubTask>() {
			@Override
			public SubTask extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if(rs.next()) {
					return subTaskPackage(rs);
				} else {
					return null;
				}
			}
			
		});
		jdbcTemplate.update(UPDATE_SUB_TASK_STATUS, Constants.TASK_STATUS_INIT, subTaskId);
		jdbcTemplate.update(UPDATE_TASK_STATUS, Constants.TASK_STATUS_INIT, subTask.getTask_id());
	}
	
	@Override
	public void stopTask(long task_id) {
		jdbcTemplate.update(UPDATE_TASK_STATUS, Constants.TASK_STATUS_STOP, task_id);
		jdbcTemplate.update(STOP_SUB_TASK, task_id);
	}
	
	private VideoTaskBean videoTaskPackage(ResultSet rs) {
		VideoTaskBean videoTaskBean = new VideoTaskBean();
		try {
			videoTaskBean.setVid(rs.getLong("vid"));
			videoTaskBean.setUrl(rs.getString("url"));
			videoTaskBean.setTitle(rs.getString("title"));
			videoTaskBean.setPlatform(rs.getInt("platform"));
			videoTaskBean.setStatus(rs.getInt("status"));
//			videoTaskBean.setStart_time(rs.getTime("start_time"));
			videoTaskBean.setStart_time(rs.getTimestamp("start_time"));
			videoTaskBean.setEnd_time(rs.getTimestamp("end_time"));
			videoTaskBean.setReset_time(rs.getTime("reset_time"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return videoTaskBean;
	}
	
	private SubVideoTaskBean subVideoTaskPackage(ResultSet rs) {
		SubVideoTaskBean subVideoTaskBean = new SubVideoTaskBean();
		try{
			subVideoTaskBean.setSub_vid(rs.getLong("sub_vid"));
			subVideoTaskBean.setVid(rs.getLong("vid"));
			subVideoTaskBean.setPage_url(rs.getString("page_url"));
			subVideoTaskBean.setPlatform(rs.getInt("platform"));
			subVideoTaskBean.setTitle(rs.getString("title"));
			subVideoTaskBean.setStatus(rs.getInt("status"));
			subVideoTaskBean.setAdd_time(rs.getTimestamp("add_time"));
			subVideoTaskBean.setLast_update_time(rs.getTimestamp("last_update_time"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return subVideoTaskBean;
	}

	private Task taskPackage(ResultSet rs) {
		Task task = new Task();
		try {
			task.setTask_id(rs.getLong("task_id"));
			task.setKey_word(rs.getString("key_word"));
			task.setStatus(rs.getInt("status"));
			task.setStart_time(rs.getDate("start_time"));
			task.setEnd_time(rs.getDate("end_time"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return task;
	}
	
	private SubTask subTaskPackage(ResultSet rs) {
		SubTask subTask = new SubTask();
		try {
			subTask.setSub_task_id(rs.getLong("sub_task_id"));
			subTask.setTask_id(rs.getLong("task_id"));
			subTask.setUrl(rs.getString("url"));
			subTask.setPlatform(rs.getInt("platform"));
			subTask.setStatus(rs.getInt("status"));
			subTask.setStart_time(rs.getDate("start_time"));
			subTask.setEnd_time(rs.getDate("end_time"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return subTask;
	}
}
