/**
 *
 * Copyright (C) 2009 Cloud Conscious, LLC. <info@cloudconscious.com>
 *
 * ====================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ====================================================================
 */
package org.jclouds.aws.ec2.domain;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Set;

import org.jclouds.aws.domain.Region;

import com.google.inject.internal.Nullable;

/**
 * 
 * @see <a href="http://docs.amazonwebservices.com/AWSEC2/latest/APIReference/ApiReference-ItemType-ReservationInfoType.html"
 *      />
 * @author Adrian Cole
 */
public class Reservation implements Comparable<Reservation> {

   private final Region region;
   private final Set<String> groupIds;
   private final Set<RunningInstance> instances;
   private final @Nullable
   String ownerId;
   private final @Nullable
   String requesterId;
   private final @Nullable
   String reservationId;

   public Reservation(Region region, Set<String> groupIds, Set<RunningInstance> instances,
            @Nullable String ownerId, @Nullable String requesterId, @Nullable String reservationId) {
      this.region = checkNotNull(region, "region");
      this.groupIds = checkNotNull(groupIds, "groupIds");
      this.instances = checkNotNull(instances, "instances");
      this.ownerId = ownerId;
      this.requesterId = requesterId;
      this.reservationId = reservationId;
   }

   /**
    * Instances are tied to Availability Zones. However, the instance ID is tied to the Region.
    */
   public Region getRegion() {
      return region;
   }

   public int compareTo(Reservation o) {
      return (this == o) ? 0 : getReservationId().compareTo(o.getReservationId());
   }

   /**
    * Names of the security groups.
    */
   public Set<String> getGroupIds() {
      return groupIds;
   }

   public Set<RunningInstance> getRunningInstances() {
      return instances;
   }

   /**
    * AWS Access Key ID of the user who owns the reservation.
    */
   public String getOwnerId() {
      return ownerId;
   }

   /**
    * ID of the requester.
    */
   public String getRequesterId() {
      return requesterId;
   }

   /**
    * Unique ID of the reservation.
    */
   public String getReservationId() {
      return reservationId;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((groupIds == null) ? 0 : groupIds.hashCode());
      result = prime * result + ((instances == null) ? 0 : instances.hashCode());
      result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
      result = prime * result + ((region == null) ? 0 : region.hashCode());
      result = prime * result + ((requesterId == null) ? 0 : requesterId.hashCode());
      result = prime * result + ((reservationId == null) ? 0 : reservationId.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      Reservation other = (Reservation) obj;
      if (groupIds == null) {
         if (other.groupIds != null)
            return false;
      } else if (!groupIds.equals(other.groupIds))
         return false;
      if (instances == null) {
         if (other.instances != null)
            return false;
      } else if (!instances.equals(other.instances))
         return false;
      if (ownerId == null) {
         if (other.ownerId != null)
            return false;
      } else if (!ownerId.equals(other.ownerId))
         return false;
      if (region == null) {
         if (other.region != null)
            return false;
      } else if (!region.equals(other.region))
         return false;
      if (requesterId == null) {
         if (other.requesterId != null)
            return false;
      } else if (!requesterId.equals(other.requesterId))
         return false;
      if (reservationId == null) {
         if (other.reservationId != null)
            return false;
      } else if (!reservationId.equals(other.reservationId))
         return false;
      return true;
   }

}