package seedu.address.model.task;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Task in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {

    private final TaskName name;
    private final Deadline deadline;
    private final Set<Tag> tags = new HashSet<>();
    private boolean isDone;

    /**
     * Constructs a {@code Task}.
     *
     * @param name A valid TaskName.
     * @param deadline A valid Deadline.
     * @param tags A valid Set of Tags.
     */
    public Task(TaskName name, Deadline deadline, Set<Tag> tags) {
        this.name = name;
        this.deadline = deadline;
        this.tags.addAll(tags);
        this.isDone = false;
    }

    public TaskName getName() {
        return name;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public String getStatus() {
        return this.isDone ? "Completed" : "Pending";
    }

    public boolean checkIsDone() {
        return isDone;
    }

    public void markTaskComplete() {
        this.isDone = true;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both Tasks have the same name.
     * This defines a weaker notion of equality between two Task.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getName().equals(getName());
    }

    /**
     * Returns true if both tasks have the same identity and data fields.
     * This defines a stronger notion of equality between two tasks.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getName().equals(getName())
                && otherTask.getDeadline().equals(getDeadline())
                && otherTask.getTags().equals(getTags());
    }



    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, deadline, tags, isDone);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Deadline: ")
                .append(getDeadline())
                .append("; Status: ")
                .append(getStatus());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        return builder.toString();
    }
}
