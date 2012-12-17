package is.illuminati.block.hephaestus.data;

import is.illuminati.block.hephaestus.business.LoggingCondition;
import is.illuminati.block.hephaestus.business.LoggingDirection;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.idega.user.data.bean.User;

@Entity
@Table(name = LogHeader.ENTITY_NAME)
@NamedQueries({
        @NamedQuery(name = "logHeader.findAll", query = "select l from LogHeader l")
})
public class LogHeader implements Serializable {
	private static final long serialVersionUID = -6302012344163641698L;

	public static final String ENTITY_NAME = "hep_logf";
    
    private static final String COLUMN_LOG_HEADER_ID = "logf_id";
    private static final String COLUMN_WELL = "well_id";
    private static final String COLUMN_CONDITION = "well_condition";
    private static final String COLUMN_TOOL = "tool_id";
    private static final String COLUMN_START_DATE = "date_start";
    private static final String COLUMN_END_DATE = "date_end";
    private static final String COLUMN_SHIFT = "shift";
    private static final String COLUMN_LOG_DIRECTION = "logdirection";
    private static final String COLUMN_ENGINEERS = "engr";    
    private static final String COLUMN_COMMENT = "comment";
    private static final String COLUMN_SERVICE_COMPANY = "service_company";
    private static final String COLUMN_CREATED_DATE = "created";
    private static final String COLUMN_CREATED_BY = "created_by_id";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = LogHeader.COLUMN_LOG_HEADER_ID)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = LogHeader.COLUMN_WELL, nullable = false)
    private Well well;

    @Column(name = LogHeader.COLUMN_CONDITION, nullable = false)
    @Enumerated(EnumType.STRING)
    private LoggingCondition condition;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = LogHeader.COLUMN_TOOL, nullable = false)
    private Tool tool;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = LogHeader.COLUMN_START_DATE, nullable = false)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = LogHeader.COLUMN_END_DATE)
    private Date endDate;

    @Column(name = LogHeader.COLUMN_SHIFT)
    private Double shift;

    @Column(name = LogHeader.COLUMN_LOG_DIRECTION, nullable = false)
    @Enumerated(EnumType.STRING)
    private LoggingDirection logDirection;
    
    @Column(name = LogHeader.COLUMN_ENGINEERS, length = 1000)
    private String engineers;

    @Column(name = LogHeader.COLUMN_COMMENT, length = 4000)
    private String comment;

    @Column(name = LogHeader.COLUMN_SERVICE_COMPANY, length = 1000)
    private String serviceCompnay;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = LogHeader.COLUMN_CREATED_DATE, nullable = false)
    private Date createdDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = LogHeader.COLUMN_CREATED_BY, nullable = false)
    private User createdBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Well getWell() {
		return well;
	}

	public void setWell(Well well) {
		this.well = well;
	}

	public LoggingCondition getCondition() {
		return condition;
	}

	public void setCondition(LoggingCondition condition) {
		this.condition = condition;
	}

	public Tool getTool() {
		return tool;
	}

	public void setTool(Tool tool) {
		this.tool = tool;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Double getShift() {
		return shift;
	}

	public void setShift(Double shift) {
		this.shift = shift;
	}

	public LoggingDirection getLogDirection() {
		return logDirection;
	}

	public void setLogDirection(LoggingDirection logDirection) {
		this.logDirection = logDirection;
	}

	public String getEngineers() {
		return engineers;
	}

	public void setEngineers(String engineers) {
		this.engineers = engineers;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getServiceCompnay() {
		return serviceCompnay;
	}

	public void setServiceCompnay(String serviceCompnay) {
		this.serviceCompnay = serviceCompnay;
	}
}